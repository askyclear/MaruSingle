package com.hudini.korea.marusingle.view.maru.contract;

import com.hudini.korea.marusingle.core.BasePresenter;
import com.hudini.korea.marusingle.core.BaseView;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;
import com.hudini.korea.marusingle.model.maru.MaruItem;

import java.util.List;

/**
 * Created by korea on 2018-04-05.
 */

public interface MaruContract {
    interface View extends BaseView<Presenter>{
        public void setInitViews(MaruItem maruItem);

        void goToViewer(MaruContentItem item);

        void setSpinnerView(List<MaruContentItem> contentItemList);

        void progressShow();

        void progressClose();
    }
    interface  Presenter extends BasePresenter{

        void reverseSort();
    }
}
