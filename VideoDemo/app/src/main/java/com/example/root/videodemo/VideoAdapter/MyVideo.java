package com.example.root.videodemo.VideoAdapter;

public class MyVideo{
    //都是只读成员变量
    private String mAuthor;
    private String mDescription;
    private String mUrl;
    public MyVideo(String url){
        mAuthor ="";
        mDescription="";
        this.mUrl = url;
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
