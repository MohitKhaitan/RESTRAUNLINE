package com.appodroid.mohitkhaitan.restraunline.Manager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appodroid.mohitkhaitan.restraunline.Helper.Config;
import com.appodroid.mohitkhaitan.restraunline.Helper.SessionManager;
import com.appodroid.mohitkhaitan.restraunline.ParseNotification.ParseUtils;
import com.appodroid.mohitkhaitan.restraunline.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Saurabh on 20-03-2016.
 */
public class SignUpActivityManager extends AppCompatActivity {
    ProgressDialog progress;
    String NAME, EMAIL, PASSWORD;
    EditText name,email,password;
    Button sign_up;
    SessionManager sessionManager;

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ParseUtils.verifyParseConfiguration(this);

        setContentView(R.layout.sign_up_activity_manager);

        name = (EditText)findViewById(R.id.input_name);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        sign_up = (Button)findViewById(R.id.btn_signup);
        sessionManager = new SessionManager(getApplicationContext());


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NAME = name.getText().toString().trim();
                EMAIL = email.getText().toString().trim();
                PASSWORD = password.getText().toString();

                if (NAME.isEmpty()) {
                    name.setError("Enter Name");
                } else if (EMAIL.isEmpty()) {
                    email.setError("Enter Email");
                } else if (PASSWORD.isEmpty()) {
                    password.setError("Enter Password");
                } else {
                    sessionManager.createLoginSession(NAME, EMAIL);

                    new PostClass(SignUpActivityManager.this).execute();
                    Intent singUpIntent = new Intent(getApplicationContext(), MainActivityManager.class);
                    startActivity(singUpIntent);
                    finish();
                }
            }
        });

    }

    //Inner Private Class To Connect & Send Data To Server
    private class PostClass extends AsyncTask<String, Void, Void> {

        private final Context context;
        StringBuilder responseOutput;
        public PostClass(Context c){
            this.context = c;
        }

        @Override
        protected void onPreExecute(){
            progress= new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                URL url = new URL(Config.domain+"/Register.php");

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                String urlParameters = "email="+SignUpActivityManager.this.EMAIL+"&password="+SignUpActivityManager.this.PASSWORD+
                        "&name="+SignUpActivityManager.this.NAME +"&type=0";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);

                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();

                int responseCode = connection.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                responseOutput = new StringBuilder();

                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();


                SignUpActivityManager.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //outputView.setText(output);
                        progress.dismiss();
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void res)
        {
            super.onPostExecute(res);
            if(responseOutput.equals("false"))
                Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),responseOutput,Toast.LENGTH_SHORT).show();


        }
    }

}
