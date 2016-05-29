package com.sosoham.sosoham;

/**
 * Created by Yeong Jin on 2016-05-29.
 */
public class CommentListItem {
    private String profileUrl;
    private String name;
    private int type;
    public String GetProfileUrl() {
        return profileUrl;
    }
    public String GetName() {
        return name;
    }
    public int type() {
        return type;
    }
    public CommentListItem(String profileUrl, String name, int type) {
        this.profileUrl = profileUrl;
        this.name = name;
        this.type = type;
    }
}
