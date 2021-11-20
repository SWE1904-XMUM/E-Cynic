package com.example.e_cynic.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager
{
    // Initialize variable
    SharedPreferences sp;
    SharedPreferences.Editor edt;

    // Constructor
    public SessionManager(Context context)
    {
        sp = context.getSharedPreferences("E-Cynic",0);
        edt = sp.edit();
        edt.apply();
    }

    // Set login
    public void setLogin(boolean login)
    {
        edt.putBoolean("KEY_LOGIN",login);
        edt.commit();
    }

    // Get login
    public boolean getLogin()
    {
        return sp.getBoolean("KEY_LOGIN",false);
    }

    // Get username
    public void setUsername(String uname)
    {
        edt.putString("KEY_USERNAME",uname);
        edt.commit();
    }

    // Get username
    public String getUsername()
    {
        return sp.getString("KEY_USERNAME","");
    }
}
