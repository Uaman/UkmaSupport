package com.ukmaSupport.services;

import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void init() {
        userService = mock(UserService.class);
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testGetResponsibleAssistant() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSaveOrUpdate(){
        int size = userService.getAll().size();
        User user = new User();
        user.setFirstName("тест");
        user.setLastName("тест");
        user.setEmail("test@gmail.com");
        user.setPassword("test");
        user.setRole("ADMIN");
        user.setAccountStatus("active");
        userService.saveOrUpdate(user);

        // list should have one more user now
        //assertTrue (size < userService.getAll().size());чому??
    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetByEmail() throws Exception {

    }

    @Test
    public void testGetByRole() throws Exception {

    }

    @Test
    public void testGetByStatus() throws Exception {

    }
}