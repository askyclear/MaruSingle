package com.hudini.korea.marusingle.view.main.contract;


import com.hudini.korea.marusingle.core.BasePresenter;
import com.hudini.korea.marusingle.core.BaseView;
import com.hudini.korea.marusingle.model.maru.MaruItem;

/**
 * Created by korea on 2018-03-13.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void progressShow();
        void progressClose();
        void goToMaruActivity(MaruItem maruItem);
    }
    interface Presenter extends BasePresenter{

        void clearView();

        void isChanged();

        void nextPage();
    }
}
