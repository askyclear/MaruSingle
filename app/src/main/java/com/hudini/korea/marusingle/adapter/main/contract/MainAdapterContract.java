package com.hudini.korea.marusingle.adapter.main.contract;

import com.hudini.korea.marusingle.core.BaseAdapterPresenter;
import com.hudini.korea.marusingle.core.BaseAdapterView;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruItem;

import java.util.List;

/**
 * Created by korea on 2018-03-26.
 */

public interface MainAdapterContract {
    interface View extends BaseAdapterView{
        void setOnItemClickListener(OnItemClickListener listener);
        void refreshPosition(int position);

        void refreshRange(int oldPosition, int size);
    }
    interface Presenter extends BaseAdapterPresenter<MaruItem>{
        void addList(List<MaruItem> maruItems);
        void setItem(int position, MaruItem maruItem);
    }
}
