package com.hudini.korea.marusingle.util.crawler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;

/**
 * Created by korea on 2018-04-01.
 * 현재 기기의 사이즈를 가져오기 위한 유틸 클래스
 */

public final class DisplayUtil {
    /**
     *
     * @param context 현재 activity의 context를 가져온다.
     * @return Point 객체 반환
     */
    public static Point containerSize(@NonNull Context context){
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
