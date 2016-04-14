package com.appodroid.mohitkhaitan.restraunline.Manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appodroid.mohitkhaitan.restraunline.R;

/**
 * Created by MOHIT KHAITAN on 19-03-2016.
 */
public class ProfileFragmentManager extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile_man, container, false);
        return rootView;

    }
}
