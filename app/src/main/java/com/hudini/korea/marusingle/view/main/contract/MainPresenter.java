package com.hudini.korea.marusingle.view.main.contract;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.hudini.korea.marusingle.adapter.main.MainAdapter;
import com.hudini.korea.marusingle.adapter.main.contract.MainAdapterContract;
import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.model.maru.SharedSource;
import com.hudini.korea.marusingle.model.maru.repository.SharedRepository;
import com.hudini.korea.marusingle.util.crawler.MaruUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by korea on 2018-03-13.
 * 메인 activity와 Model 간의 통신 중제
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private MainAdapterContract.View mAdaoterView;
    private MainAdapterContract.Presenter mAdapterPresenter;
    private Context context;
    private int curPage;
    volatile List<MaruTag> beforeTags;

    public MainPresenter(MainContract.View view, MainAdapter mainAdapter) {
        this.mView = view;
        this.context = (Context) view;
        mAdaoterView = mainAdapter;
        mAdapterPresenter = mainAdapter;
        beforeTags = new ArrayList<>();
        this.curPage = 1;
        mAdaoterView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mView.goToMaruActivity(mAdapterPresenter.getItem(position));
            }
        });

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        SharedRepository repository = SharedRepository.getInstance();
        repository.getSharedes(context, new SharedSource.LoadSharedCallback() {
            @Override
            public void onSharedLoad(Map<String, Boolean> isSheard) {
                List<MaruTag> maruTags = new ArrayList<>();
                for (MaruTag tag : MaruTag.values()) {
                    if (isSheard.containsKey(tag.toString())) {
                        if (isSheard.get(tag.toString())) {
                            maruTags.add(tag);
                        }
                    }
                }
                curPage = 1;
                MaruTag[] tags = new MaruTag[maruTags.size()];
                maruTags.toArray(tags);
                mAdapterPresenter.clear();
                mAdaoterView.refresh();
                beforeTags.clear();
                beforeTags.addAll(maruTags);
                MaruAsyncTask task = new MaruAsyncTask();
                Log.d("CUR_PAGE", "" + curPage);
                task.execute(tags);
            }
        });

    }

    @Override
    public void isChanged() {
        SharedRepository repository = SharedRepository.getInstance();
        repository.getSharedes(context, new SharedSource.LoadSharedCallback() {
            @Override
            public void onSharedLoad(Map<String, Boolean> isSheard) {
                List<MaruTag> maruTags = new ArrayList<>();
                for (MaruTag tag : MaruTag.values()) {
                    if (isSheard.containsKey(tag.toString())) {
                        if (isSheard.get(tag.toString())) {
                            Log.d("select tag : ", tag.toString());
                            maruTags.add(tag);
                        }
                    }
                }
                if (!maruTags.containsAll(beforeTags)) {
                    Log.d("before size : ", beforeTags.size() + "");
                    start();
                }
            }
        });
    }

    @Override
    public void nextPage() {
        SharedRepository repository = SharedRepository.getInstance();
        repository.getSharedes(context, new SharedSource.LoadSharedCallback() {
            @Override
            public void onSharedLoad(Map<String, Boolean> isSheard) {
                List<MaruTag> maruTags = new ArrayList<>();
                for (MaruTag tag : MaruTag.values()) {
                    if (isSheard.containsKey(tag.toString())) {
                        if (isSheard.get(tag.toString())) {
                            maruTags.add(tag);
                        }
                    }
                }
                curPage++;
                MaruTag[] tags = new MaruTag[maruTags.size()];
                maruTags.toArray(tags);
                beforeTags.clear();
                beforeTags.addAll(maruTags);
                MaruAsyncTask task = new MaruAsyncTask();
                Log.d("CUR_PAGE", "" + curPage);
                task.execute(tags);
            }
        });
    }

    @Override
    public void clearView() {
        mView = null;
    }

    private class MaruAsyncTask extends AsyncTask<MaruTag, Void, Void> {
        private List<MaruItem> items;
        private int oldPosition;

        public MaruAsyncTask() {
            this.items = new ArrayList<>();
            this.oldPosition = mAdapterPresenter.getSize();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mView.progressShow();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapterPresenter.addList(items);
            if (oldPosition < mAdapterPresenter.getSize() - 1)
                mAdaoterView.refreshRange(oldPosition, mAdapterPresenter.getSize() - 1);

            mView.progressClose();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(MaruTag... tags) {
            if (!isCancelled()) {
                if (tags != null) {
                    //처음에만 else로 넘어감
                    if (tags.length != 0) {
                        for (int i = 0; i < tags.length; i++) {
                            items.addAll(MaruUtil.getInstance().searchPageList(curPage, tags[i]));
                        }

                    } else {
                        items.addAll(MaruUtil.getInstance().searchPageList(curPage, MaruTag.DEFAULT));
                        SharedRepository.getInstance().setShared(context,MaruTag.DEFAULT,true);
                    }
                    Collections.sort(items, new Comparator<MaruItem>() {
                        @Override
                        public int compare(MaruItem maruItem, MaruItem t1) {
                            return maruItem.compareTo(t1);
                        }
                    });
                }
            }
            Log.d("MaruTask : ", " Finish");
            return null;
        }
    }
}
