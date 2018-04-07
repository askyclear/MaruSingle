package com.hudini.korea.marusingle.model.maru;

import android.content.Context;

import com.hudini.korea.marusingle.constant.MaruTag;

import java.util.Map;

/**
 * Created by korea on 2018-03-27.
 */

public interface SharedSource {
    interface LoadSharedCallback{
        void onSharedLoad(Map<String,Boolean> isSheard);
    }
    void getSharedes(Context context, LoadSharedCallback loadSharedCallback);
    void setShared(Context context, MaruTag tag, boolean isShared);
}
