package com.example.root.videodemo.widget;

public class MyVideo {
    //都是只读成员变量
    private String mAuthor;
    private String mDescription;

    public void setUrl(String url) {
        mUrl = url;
    }

    private String mUrl;
    public MyVideo(){
        mAuthor ="";
        mDescription="";
        mUrl = "";
    }


    public String getAuthor() {
        return mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
