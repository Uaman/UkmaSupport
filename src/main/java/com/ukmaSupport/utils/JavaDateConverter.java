package com.ukmaSupport.utils;

public class JavaDateConverter {

    public static java.sql.Date convertToSqlDate(java.util.Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

    public static java.util.Date convertToJavaDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }
}
