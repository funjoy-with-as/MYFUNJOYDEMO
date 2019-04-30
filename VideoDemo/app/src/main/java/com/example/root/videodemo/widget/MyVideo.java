package com.example.root.videodemo.widget;

public class MyVideo{
    //都是只读成员变量
    private String mAuthor;
    private String mDescription;
    private String mUrl;
    public MyVideo(){
        mAuthor ="root";
        mDescription="";
        mUrl = "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0";
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
