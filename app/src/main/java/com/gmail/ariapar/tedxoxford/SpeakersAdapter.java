package com.gmail.ariapar.tedxoxford;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by AriApar on 24/12/14.
 */
public class SpeakersAdapter extends ArrayAdapter<NewsData> {
    ImageLoader imageLoader;

    public SpeakersAdapter(Context context, ArrayList<NewsData> newsItems) {
        super(context, 0, newsItems);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        NewsData data = getItem(position);

        //if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.speakers_rowlayout, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.firstLine);

        // title
        title.setText(data.getTitle());

        return convertView;
    }



}