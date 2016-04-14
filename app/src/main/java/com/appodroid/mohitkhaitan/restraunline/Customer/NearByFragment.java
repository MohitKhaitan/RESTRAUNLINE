package com.appodroid.mohitkhaitan.restraunline.Customer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.appodroid.mohitkhaitan.restraunline.Adapter.HotelAdapter;
import com.appodroid.mohitkhaitan.restraunline.Helper.Config;
import com.appodroid.mohitkhaitan.restraunline.Model.HotelList;
import com.appodroid.mohitkhaitan.restraunline.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by MOHIT KHAITAN on 19-03-2016.
 */
public class NearByFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList<HotelList> hotelLists;
    HotelAdapter hotelAdapter;
    String URL = Config.domain+"/HotelList.php";
    ListView lv;
    private SwipeRefreshLayout swipeRefreshLayout;
    GetHotelList getHotelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearby_cust, container, false);

        lv = (ListView)rootView.findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getHotelList = new GetHotelList();
                getHotelList.execute();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent item = new Intent(getActivity(),OrderActivity.class);
                startActivity(item);
            }
        });

        return rootView;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(),"Data Change Reflected",Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    class GetHotelList extends AsyncTask<Object, Object, Object>{

        @Override
        protected Object doInBackground(Object... params) {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(URL);
            ResponseHandler<String> handler = new BasicResponseHandler();
            Object result = new Object();
            Log.e("Json Resopnse=====", result.toString());

            try{
                result = client.execute(request, handler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Object result){
            String json = result.toString();
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(json).getAsJsonArray();

            hotelLists = new ArrayList<HotelList>();

            for(JsonElement obj : jArray){

                HotelList hl = gson.fromJson(obj, HotelList.class);
                hotelLists.add(hl);
            }

            lv.setAdapter(new HotelAdapter(getActivity(), R.layout.hotel_list_view, hotelLists));
        }
    }
}
