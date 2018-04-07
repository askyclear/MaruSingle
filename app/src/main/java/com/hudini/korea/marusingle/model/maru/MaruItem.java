package com.hudini.korea.marusingle.model.maru;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by korea on 2018-03-26.
 */

public class MaruItem implements Comparable<MaruItem>{
    private long uId;
    private String title;
    private String type;
    private String thumnail_url;
    private Bitmap thumnail_bitmap;

    public MaruItem(long uId, String title, String type, String thumnail_url,Bitmap thumnail_bitmap) {
        this.uId = uId;
        this.title = title;
        this.thumnail_url = thumnail_url;
        this.type = type;
        this.thumnail_bitmap = thumnail_bitmap;
    }

    public Bitmap getThumnail_bitmap() {
        return thumnail_bitmap;
    }

    public long getuId() {
        return uId;
    }

    public String getTitle() {
        return title;
    }

    public String getThumnail_url() {
        return thumnail_url;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(@NonNull MaruItem maruItem) {
        if(maruItem.getuId()<uId){
            return 1;
        }else if(maruItem.getuId()>uId){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MaruItem){
            MaruItem maruItem = (MaruItem) obj;
            if(maruItem.getuId()==uId)
                return true;
        }
        return false;
    }
}
