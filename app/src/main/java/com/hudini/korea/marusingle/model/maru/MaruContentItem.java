package com.hudini.korea.marusingle.model.maru;

/**
 * Created by korea on 2018-04-04.
 */

public class MaruContentItem {
    private long uId;
    private String chapterTitle;
    private String chapterUrl;

    public MaruContentItem(long uId, String chapterTitle, String chapterUrl) {
        this.uId = uId;
        this.chapterTitle = chapterTitle;
        this.chapterUrl = chapterUrl;
    }

    public long getuId() {
        return uId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }
}
