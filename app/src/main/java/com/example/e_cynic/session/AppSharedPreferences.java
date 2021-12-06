package com.example.e_cynic.session;

import com.example.e_cynic.db.SharedPreferencesDatabase;

public class AppSharedPreferences {
    public static String sharedPreferencesFile;

    public static boolean updateUser(String username) {
        sharedPreferencesFile = SharedPreferencesDatabase.getFileByUsername(username); //get sp file name
        if(sharedPreferencesFile == "") {
            boolean result = SharedPreferencesDatabase.createNewSpFile(username);
            if(result == false) {
                return false;
            }
            updateUser(username);
        }
        return true;
    }

    public static String getSpFile() {
        return sharedPreferencesFile;
    }
}
