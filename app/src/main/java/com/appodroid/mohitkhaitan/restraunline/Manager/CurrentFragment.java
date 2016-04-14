package com.appodroid.mohitkhaitan.restraunline.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appodroid.mohitkhaitan.restraunline.OtherActivity.NotificationActivity;
import com.appodroid.mohitkhaitan.restraunline.R;

/**
 * Created by MOHIT KHAITAN on 19-03-2016.
 */
public class CurrentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button notify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_man, container, false);

        notify = (Button)rootView.findViewById(R.id.notify);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), NotificationActivity.class);
                startActivity(in);
            }
        });
        return rootView;
    }



}
