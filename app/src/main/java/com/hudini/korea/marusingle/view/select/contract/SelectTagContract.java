package com.hudini.korea.marusingle.view.select.contract;

import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.core.BasePresenter;
import com.hudini.korea.marusingle.core.BaseView;

/**
 * Created by korea on 2018-04-02.
 */

public interface SelectTagContract {
    interface View extends BaseView<Presenter>{

    }
    interface Presenter extends BasePresenter{
        void checkTag(MaruTag maruTag);
    }
}
