package com.appodroid.mohitkhaitan.restraunline.Customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appodroid.mohitkhaitan.restraunline.Helper.Config;
import com.appodroid.mohitkhaitan.restraunline.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MOHIT KHAITAN on 07-04-2016.
 */
public class OrderActivity extends AppCompatActivity {

    EditText mobile, orderItems;
    Button placeOrder;
    String MOBILE, ITEMS;
    ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mobile = (EditText)findViewById(R.id.mobile);
        orderItems = (EditText)findViewById(R.id.items);
        placeOrder = (Button)findViewById(R.id.place_order);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MOBILE = mobile.getText().toString().trim();
                ITEMS = orderItems.getText().toString().trim();

                new OrderClass(OrderActivity.this).execute();
            }
        });
    }

    private class OrderClass extends AsyncTask<String, Void, Void> {

        private final Context context;
        StringBuilder responseOutput;
        public OrderClass(Context c){
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

                // final TextView outputView = (TextView) findViewById(R.id.showOutput);
                URL url = new URL(Config.domain+"/SetOrder.php");

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                String urlParameters = "hid=2"+"&uid=3"+"&mobile="+OrderActivity.this.MOBILE+"&items="+OrderActivity.this.ITEMS;
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


                OrderActivity.this.runOnUiThread(new Runnable() {

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
                Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),responseOutput,Toast.LENGTH_SHORT).show();


        }
    }
}
