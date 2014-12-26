package com.gmail.ariapar.tedxoxford;

import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by AriApar on 22/12/14.
 */
public class NewsDataBuilder {

    public ArrayList<NewsData> newsDataFromJSON(JSONObject response) {

        ArrayList<NewsData> newsList = new ArrayList<NewsData>();

            try {

                JSONArray results = response.getJSONArray("posts");

                for (int j = 0; j < results.length(); j++) {

                    JSONObject item = results.getJSONObject(j);

                    NewsData news = new NewsData();


                    //TODO: Take care of these
                    //Set title
                    if (!item.isNull("title_plain"))
                        news.setTitle(Html.fromHtml(item.getString("title_plain")).toString());

                    //Set content
                    if (!item.isNull("content")) {
                        String c = item.getString("content");
                        String content = "<html><head><style>img{width:100%%;height:auto}" +
                                "iframe{width:100%%;}.aspect-ratio {position: relative;width: 100%%;" +
                                "height: 0;padding-bottom: 56.25%%;}.aspect-ratio iframe {width: 100%%;" +
                                "height: 100%%;};</style></head><body><div class=aspect-ratio>" +
                                c +
                                "</div></body></html>";


                        news.setContent(content);
                    }
                    //Set id
                    if (!item.isNull("id"))
                        news.setPostId(Integer.toString(item.getInt("id")));

                    //Set excerpt
                    if (!item.isNull("excerpt"))
                        news.setExcerpt(Html.fromHtml(item.getString("excerpt")).toString());
                    //Set thumbnail image URL

                    if (!item.isNull("thumbnail_images")) news.setThumbnailImage(
                            item.getJSONObject("thumbnail_images")
                                    .getJSONObject("full")
                                    .getString("url"));

                    newsList.add(news);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return newsList;
    }

    public ArrayList<NewsData> speakerDataFromJSON(JSONObject results) {

        ArrayList<NewsData> data = newsDataFromJSON(results);
        ArrayList<NewsData> spData = new ArrayList<NewsData>();
        for (NewsData item : data){
            String titleFormat = "TEDxOxford 2015 Speaker ";
            item.setTitle(item.getTitle().substring(titleFormat.length()));
            spData.add(item);
        }

        return spData;
    }

    public NewsData pageDataFromJSON(JSONObject results){
        NewsData data = new NewsData();

        try {
            JSONObject page = results.getJSONObject("page");


            data.setTitle(Html.fromHtml(page.getString("title_plain")).toString());

//Set content

            String c = page.getString("content");
            String content = "<html><head><style>img{width:100%%;height:auto}" +
                    "iframe{width:100%%;}.aspect-ratio {position: relative;width: 100%%;" +
                    "height: 0;padding-bottom: 56.25%%;}.aspect-ratio iframe {width: 100%%;" +
                    "height: 100%%;};</style></head><body><div class=aspect-ratio>" +
                    c +
                    "</div></body></html>";


            data.setContent(content);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return data;

    }
}
