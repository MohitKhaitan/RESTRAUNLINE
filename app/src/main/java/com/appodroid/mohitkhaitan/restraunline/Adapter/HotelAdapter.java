package com.appodroid.mohitkhaitan.restraunline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appodroid.mohitkhaitan.restraunline.Model.HotelList;
import com.appodroid.mohitkhaitan.restraunline.R;

import java.util.ArrayList;

/**
 * Created by MOHIT KHAITAN on 27-03-2016.
 */
public class HotelAdapter extends ArrayAdapter {

    int resource;
    String response;
    Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HotelList> hotelList;

    public HotelAdapter(Context context, int resource, ArrayList<HotelList> hotelList) {
        super(context, resource, hotelList);
        this.resource = resource;
        this.hotelList = new ArrayList<HotelList>();
        this.hotelList.addAll(hotelList);
        mLayoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        HotelList hotelList = (HotelList) getItem(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.hotel_list_view, null);
        }
        TextView hotel_name = (TextView)convertView.findViewById(R.id.hotel_name);
        TextView hotel_city = (TextView)convertView.findViewById(R.id.city);

        hotel_name.setText(hotelList.getHotelName());
        hotel_city.setText(hotelList.getCity());

        return convertView;
    }
}
