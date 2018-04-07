package com.hudini.korea.marusingle.view.select.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.hudini.korea.marusingle.adapter.select.SelectTagAdapter;
import com.hudini.korea.marusingle.adapter.select.contract.SelectTagAdapterContract;
import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruTagItem;
import com.hudini.korea.marusingle.model.maru.SharedSource;
import com.hudini.korea.marusingle.model.maru.repository.SharedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by korea on 2018-04-02.
 * this class is SelectActivity Presenter
 */

public class SelectTagPresenter implements SelectTagContract.Presenter {
    private SelectTagContract.View view;
    private SelectTagAdapterContract.View adapterView;
    private SelectTagAdapterContract.Presenter adapterPresenter;
    private Context context;

    public SelectTagPresenter(SelectTagContract.View view, final SelectTagAdapter adapter) {
        this.context = (Context) view;
        this.view = view;
        this.adapterView = adapter;
        this.adapterPresenter = adapter;
        this.adapterView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                final SharedRepository repository = SharedRepository.getInstance();
                MaruTagItem item = adapterPresenter.getItem(position);
                //전체보기를 선택하면
                if (item.getTag().equals(MaruTag.DEFAULT)) {
                    for (MaruTag tag : MaruTag.values()) {
                        repository.setShared(context, tag, false);
                    }
                    repository.setShared(context, item.getTag(), true);
                } else {
                    repository.setShared(context, item.getTag(), !item.isChecked());
                    int trueCount = 0;
                    //현재 trueCount를 조사
                    for(int i = 0; i<adapterPresenter.getSize();i++){
                        if(adapterPresenter.getItem(i).isChecked())
                            trueCount++;
                    }
                    //true count가 1이고 현재 선택한 item이 true라면 이 이벤트 후 전체 false이므로 전체보기에 true를 해준다.
                    if(trueCount==1 && item.isChecked())
                        repository.setShared(context, MaruTag.DEFAULT, true);
                    else
                        repository.setShared(context, MaruTag.DEFAULT, false);
                }
                //Check된 테그 정보를 불러와서 item에 재등록
                repository.getSharedes(context, new SharedSource.LoadSharedCallback() {
                    @Override
                    public void onSharedLoad(Map<String, Boolean> isSheard) {
                        List<MaruTagItem> maruTags = new ArrayList<>();
                        for (MaruTag tag : MaruTag.values()) {

                            if (isSheard.containsKey(tag.toString())) {
                                if (isSheard.get(tag.toString())) {
                                    maruTags.add(new MaruTagItem(tag, true));
                                }
                            } else {
                                maruTags.add(new MaruTagItem(tag, false));
                            }
                        }
                        adapterPresenter.chageItems(maruTags);
                        adapterView.refresh();
                    }
                });

            }
        });
        view.setPresenter(this);
    }

    @Override
    public void start() {
        SharedRepository.getInstance().getSharedes(context, new SharedSource.LoadSharedCallback() {
            @Override
            public void onSharedLoad(Map<String, Boolean> isSheard) {
                List<MaruTagItem> maruTags = new ArrayList<>();
                for (MaruTag tag : MaruTag.values()) {

                    if (isSheard.containsKey(tag.toString())) {
                        if (isSheard.get(tag.toString())) {
                            maruTags.add(new MaruTagItem(tag, true));
                        }
                    } else {
                        maruTags.add(new MaruTagItem(tag, false));
                    }
                }
                adapterPresenter.chageItems(maruTags);
                adapterView.refresh();
            }
        });
    }

    @Override
    public void checkTag(MaruTag maruTag) {

    }
}
