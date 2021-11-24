package com.example.e_cynic.dbTests;

import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.LoggingUtil;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class UserDatabaseTest {

    private String username = "testuser";
    private String password = "1";

    @Test
    public void getUserInfo_test() throws NoSuchMethodException, NoSuchFieldException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        User user = UserDatabase.getUserInfoByUsername(username);
        LoggingUtil.printMessage("Get User Info By Username", user.toString());
    }

    @Test
    public void getIdByUsername_test() {
        int userid = UserDatabase.getUserIdByUsername(username);
        LoggingUtil.printMessage("Get Id By Username", String.valueOf(userid));
    }

    @Test
    public void verifyUser_test() {
        boolean isValid = UserDatabase.verifyUser(username, password);
        LoggingUtil.printMessage("Verify User", isValid == true ? "Valid" : "Not valid");
    }
}
