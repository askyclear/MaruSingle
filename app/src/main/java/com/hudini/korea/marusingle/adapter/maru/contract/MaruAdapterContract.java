package com.hudini.korea.marusingle.adapter.maru.contract;

import com.hudini.korea.marusingle.core.BaseAdapterPresenter;
import com.hudini.korea.marusingle.core.BaseAdapterView;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;

import java.util.List;

/**
 * Created by korea on 2018-04-04.
 */

public interface MaruAdapterContract {
    interface View extends BaseAdapterView{
        void setOnItemClickListener(OnItemClickListener listener);
    }
    interface Presenter extends BaseAdapterPresenter<MaruContentItem>{
        void addAll(List<MaruContentItem> contentItems);

        void reverseSort();
    }
}
