package com.gmail.ariapar.tedxoxford;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {

    private String title;
    private String content;
    private View rootView;

    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setRetainInstance(true);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_news_detail, container, false);

        Bundle bundle = this.getArguments();
        title = bundle.getString("title");
        content = bundle.getString("content");

        initSubViews();

        return rootView;
    }

    private void initSubViews() {

        TextView titleView = (TextView) rootView.findViewById(R.id.textView);
        WebView contentView = (WebView) rootView.findViewById(R.id.webView);
        WebSettings webSettings = contentView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        titleView.setText(title);
        contentView.loadData(content, "text/html; charset=UTF-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            initSubViews();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
