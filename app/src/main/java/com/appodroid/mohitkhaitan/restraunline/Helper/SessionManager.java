package com.appodroid.mohitkhaitan.restraunline.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.appodroid.mohitkhaitan.restraunline.OtherActivity.ChooseActivity;
import com.appodroid.mohitkhaitan.restraunline.Customer.MainActivityCustomer;
import com.appodroid.mohitkhaitan.restraunline.Manager.MainActivityManager;


/**
 * Created by MOHIT KHAITAN on 10-03-2016.
 */
public class SessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Restraunline";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";

    public static final String KEY_EMAIL = "email";

    ChooseActivity ca = new ChooseActivity();
    String choice = ca.getUserChoice();

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public String getKeyEmail(){
        return pref.getString(KEY_EMAIL, null);
    }

    public String getKeyName(){
        return pref.getString(KEY_NAME, null);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, ChooseActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }else{

            if(choice.equals("CUSTOMER")) {

                Intent in = new Intent(_context, MainActivityCustomer.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(in);

            }else{

                Intent in = new Intent(_context, MainActivityManager.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(in);

            }
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, ChooseActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
