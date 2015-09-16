package com.gmail.ariapar.tedxoxford;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public class NewsFragment extends ListFragment {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // NewsData json url
    private static final String url = "http://tedxoxford.co.uk/api/get_posts/?count=20&include=id,title,title_plain,content,excerpt,thumbnail";
    
    private ProgressDialog pDialog;
    private ArrayList<NewsData> newsList = new ArrayList<NewsData>();
    private NewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        //getActivity().setContentView(R.layout.fragment_news);
        if (newsList.size() == 0) {
            adapter = new NewsAdapter(inflater.getContext(), newsList);
            setListAdapter(adapter);

            initializeSubviews();
        }
        return rootView;
    }

    private void initializeSubviews() {
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        //getActionBar().setBackgroundDrawable(
        //  new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonObjectRequest newsReq = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        NewsDataBuilder builder = new NewsDataBuilder();
                        ArrayList<NewsData> list = builder.newsDataFromJSON(response);
                        newsList.clear();
                        newsList.addAll(list);
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(newsReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
        pDialog.dismiss();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        NewsData data = newsList.get((int) id);

        Bundle bundle = new Bundle();
        bundle.putString("title", data.getTitle());
        bundle.putString("content", data.getContent());

        NewsDetailFragment detailFragment = new NewsDetailFragment();
        detailFragment.setArguments(bundle);
        MainActivity act = (MainActivity) getActivity();
        act.fragment = detailFragment;

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(R.id.container, detailFragment);
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            initializeSubviews();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}