package com.hudini.korea.marusingle.model.maru.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.model.maru.SharedSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by korea on 2018-03-27.
 */

public class SharedLocalSource implements SharedSource {
    private static final String sharedName = "MaruTag";
    private static final SharedLocalSource instance= new SharedLocalSource();

    private SharedLocalSource() {

    }

    public static SharedLocalSource getInstance() {
        return instance;
    }

    @Override
    public void getSharedes(Context context, LoadSharedCallback loadSharedCallback) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedName,Context.MODE_PRIVATE);
        Map<String,Boolean> results = new HashMap<>();
        for(MaruTag tag : MaruTag.values()){
            if(sharedPreferences.getBoolean(tag.toString(),false)){
                results.put(tag.toString(),true);
            }
        }

        loadSharedCallback.onSharedLoad(results);
    }

    @Override
    public void setShared(Context context, MaruTag tag, boolean isShared) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(tag.toString(),isShared);
        editor.commit();
    }
}
