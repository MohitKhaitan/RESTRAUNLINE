package com.appodroid.mohitkhaitan.restraunline.OtherActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.appodroid.mohitkhaitan.restraunline.Customer.SignUpActivityCustomer;
import com.appodroid.mohitkhaitan.restraunline.Helper.SessionManager;
import com.appodroid.mohitkhaitan.restraunline.Manager.SignUpActivityManager;
import com.appodroid.mohitkhaitan.restraunline.R;

/**
 * Created by MOHIT KHAITAN on 27-02-2016.
 */
public class ChooseActivity extends AppCompatActivity {

    Button costum,rest;
    Intent signUpIntent;
    SessionManager sessionManager;
    String choice="";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }



        sessionManager = new SessionManager(getApplicationContext());

        costum = (Button)findViewById(R.id.customer);
        rest = (Button)findViewById(R.id.restaraunt);

        costum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice="CUSTOMER";
                signUpIntent = new Intent(getApplicationContext(),SignUpActivityCustomer.class);
                signUpIntent.putExtra("choise",choice);
                startActivity(signUpIntent);
                finish();
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice="MANAGER";
                signUpIntent = new Intent(getApplicationContext(),SignUpActivityManager.class);
                signUpIntent.putExtra("choice",choice);
                startActivity(signUpIntent);
                finish();
            }
        });

    }

    public String getUserChoice(){
        return choice;
    }
}
