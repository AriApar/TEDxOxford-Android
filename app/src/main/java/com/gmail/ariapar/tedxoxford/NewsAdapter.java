package com.gmail.ariapar.tedxoxford;

/**
 * Created by AriApar on 20/12/14.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class NewsAdapter extends ArrayAdapter<NewsData> {
    ImageLoader imageLoader;

    public NewsAdapter(Context context, ArrayList<NewsData> newsItems) {
        super(context, 0, newsItems);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        NewsData data = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlayout, parent, false);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.firstLine);
        TextView excerpt = (TextView) convertView.findViewById(R.id.secondLine);

        // thumbnail image
        thumbNail.setDefaultImageResId(R.drawable.ic_default_image);
        thumbNail.setImageUrl(data.getThumbnailImage(), imageLoader);

        // title
        title.setText(data.getTitle());

        // excerpt
        excerpt.setText(String.valueOf(data.getExcerpt()));

        return convertView;
    }

}