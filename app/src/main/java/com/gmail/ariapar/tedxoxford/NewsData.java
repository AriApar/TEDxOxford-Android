package com.gmail.ariapar.tedxoxford;

import android.os.Parcelable;

/**
 * Created by AriApar on 20/12/14.
 */
public class NewsData {
    private String title;
    private String content;
    private String postId;
    private String excerpt;
    private String thumbnailImage;

    public NewsData() {
    }

    public NewsData(String title, String content, String postId, String excerpt, String thumbnailImage) {
        this.title = title;
        this.content = content;
        this.postId = postId;
        this.excerpt = excerpt;
        this.thumbnailImage = thumbnailImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }
}
