package com.ukmaSupport.services;

import com.ukmaSupport.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;



@Service
public class SpringSecurityEncoder implements PasswordEncoder {

//the encode method is never called so far
//but it may be useful later
    public String encode(CharSequence charSequence) {

        String generatedSecuredPasswordHash = null;
        try {
            generatedSecuredPasswordHash = generateStrongPasswordHash(charSequence.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return generatedSecuredPasswordHash;
    }

    public User encodeUser (User user){
        String pass = user.getPassword();
        pass = encode (pass);
        user.setPassword(pass);


        return user;
    }
    static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }



    public boolean matches(CharSequence charSequence, String storedPassword) {
            String[] parts = storedPassword.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = null;
            try {
                salt = fromHex(parts[1]);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] hash = null;
            try {
                hash = fromHex(parts[2]);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            PBEKeySpec spec = new PBEKeySpec((charSequence.toString()).toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = null;
            try {
                skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] testHash = null;
            try {
                testHash = skf.generateSecret(spec).getEncoded();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        }

        private static byte[] fromHex (String hex)throws NoSuchAlgorithmException
        {
            byte[] bytes = new byte[hex.length() / 2];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
            }
            return bytes;
        }


    }
