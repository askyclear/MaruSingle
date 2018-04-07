package com.hudini.korea.marusingle.model.maru.repository;

import android.content.Context;

import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.model.maru.SharedSource;
import com.hudini.korea.marusingle.model.maru.local.SharedLocalSource;

/**
 * Created by korea on 2018-03-27.
 */

public class SharedRepository implements SharedSource {
    private SharedLocalSource sharedLocalSource;
    private static SharedRepository instance = new SharedRepository();
    private SharedRepository(){
        sharedLocalSource = SharedLocalSource.getInstance();
    }
    public static SharedRepository getInstance(){
        return instance;
    }
    @Override
    public void getSharedes(Context context, LoadSharedCallback loadSharedCallback) {
        sharedLocalSource.getSharedes(context,loadSharedCallback);
    }

    @Override
    public void setShared(Context context, MaruTag tag, boolean isShared) {
        sharedLocalSource.setShared(context,tag,isShared);
    }
}
