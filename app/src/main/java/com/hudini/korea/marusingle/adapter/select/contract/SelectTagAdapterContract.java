package com.hudini.korea.marusingle.adapter.select.contract;

import com.hudini.korea.marusingle.core.BaseAdapterPresenter;
import com.hudini.korea.marusingle.core.BaseAdapterView;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruTagItem;

import java.util.List;

/**
 * Created by korea on 2018-04-02.
 */

public interface SelectTagAdapterContract {
    interface View extends BaseAdapterView{
        void setOnItemClickListener(OnItemClickListener listener);
    }
    interface Presenter extends BaseAdapterPresenter<MaruTagItem>{
        void chageItems(List<MaruTagItem> tags);
        void chageItem(MaruTagItem tag,int position);

    }
}
