package com.hudini.korea.marusingle.view.search.contract;

import com.hudini.korea.marusingle.core.BasePresenter;
import com.hudini.korea.marusingle.core.BaseView;
import com.hudini.korea.marusingle.model.maru.MaruItem;

/**
 * Created by korea on 2018-04-08.
 */

public interface SearchContract {
    interface View extends BaseView<Presenter>{
        void setInitView();
        void showProgress();
        void dismissProgress();
        void goToMaruActivity(MaruItem maruItem);
    }
    interface  Presenter extends BasePresenter{
        void searchKeyword(String keyword);
    }
}
