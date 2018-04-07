package com.hudini.korea.marusingle.core;

import android.content.Context;

/**
 * Created by korea on 2018-03-12.
 * 모든 activity관련 뷰는 이 인터페이스를 구현 해야 하는 공통 기능?
 */

public interface BaseView<T extends BasePresenter>{
    void setPresenter(T presenter);
}
