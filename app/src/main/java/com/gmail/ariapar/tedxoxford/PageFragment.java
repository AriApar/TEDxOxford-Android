package com.gmail.ariapar.tedxoxford;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

@SuppressLint("ValidFragment")
public class PageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static String url;
    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private ProgressDialog pDialog;
    //private String mParam2;
    private View rootView;

    public PageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setRetainInstance(true);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        url = getArguments().getString("url");
        initializeSubviews();

        return rootView;
    }

    public void initializeSubviews() {


        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest newsReq = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        NewsDataBuilder builder = new NewsDataBuilder();
                        NewsData data = builder.pageDataFromJSON(response);

                        final WebView view = (WebView) rootView.findViewById(R.id.scheduleWebView);
                        view.loadData(data.getContent(), "text/html; charset=UTF-8", null);

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
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
    public void onDetach() {
        super.onDetach();
        hidePDialog();
        pDialog.dismiss();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.hide();
            //pDialog = null;
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
