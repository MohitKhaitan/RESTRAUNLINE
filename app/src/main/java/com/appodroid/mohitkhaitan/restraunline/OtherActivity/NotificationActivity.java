package com.appodroid.mohitkhaitan.restraunline.OtherActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.appodroid.mohitkhaitan.restraunline.Model.Message;
import com.appodroid.mohitkhaitan.restraunline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOHIT KHAITAN on 22-03-2016.
 */
public class NotificationActivity extends AppCompatActivity {

    private static String TAG = NotificationActivity.class.getSimpleName();
    private ListView listView;
    private List<Message> listMessages = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        listView = (ListView)findViewById(R.id.list_view);
        adapter = new MessageAdapter(this);

        listView.setAdapter(adapter);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("message");

        Message m = new Message(message, System.currentTimeMillis());
        listMessages.add(0,m);
        adapter.notifyDataSetChanged();
    }

    private class MessageAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MessageAdapter(Activity activity) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return listMessages.size();
        }

        @Override
        public Object getItem(int position) {
            return listMessages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.list_row, null);
            }

            TextView txtMessage = (TextView) view.findViewById(R.id.message);
            TextView txtTimestamp = (TextView) view.findViewById(R.id.timestamp);

            Message message = listMessages.get(position);
            txtMessage.setText(message.getMessage());

            CharSequence ago = DateUtils.getRelativeTimeSpanString(message.getTimestamp(), System.currentTimeMillis(),
                    0L, DateUtils.FORMAT_ABBREV_ALL);

            txtTimestamp.setText(String.valueOf(ago));

            return view;
        }
    }
}
