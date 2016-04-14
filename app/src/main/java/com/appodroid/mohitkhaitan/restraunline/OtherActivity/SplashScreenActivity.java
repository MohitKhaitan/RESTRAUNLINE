package com.appodroid.mohitkhaitan.restraunline.OtherActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.appodroid.mohitkhaitan.restraunline.Helper.ConnectionDetector;
import com.appodroid.mohitkhaitan.restraunline.Helper.SessionManager;
import com.appodroid.mohitkhaitan.restraunline.R;

/**
 * Created by MOHIT KHAITAN on 26-02-2016.
 */
public class SplashScreenActivity extends AppCompatActivity {

    TextView title;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalshscreen_activity);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        sessionManager = new SessionManager(getApplicationContext());

        title = (TextView)findViewById(R.id.app_title);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Libro.ttf"));


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        title.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
                        sessionManager.checkLogin();
                    }
                });
            }
        };
        thread.start();
    }

    private void checkConnection(boolean isResume) {

        if (ConnectionDetector.isConnected(this)) {
//            activityHandler();
        } else {
            ConnectionDetector.showErrorDialog(this);
            ConnectionDetector.retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isConnectionBack();
                }
            });
        }
    }

    private void isConnectionBack() {

        if (ConnectionDetector.isConnected(SplashScreenActivity.this)) {
            ConnectionDetector.dismiss();
//            activityHandler();
        }

    }
}
