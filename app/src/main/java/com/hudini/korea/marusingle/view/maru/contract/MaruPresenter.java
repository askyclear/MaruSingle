package com.hudini.korea.marusingle.view.maru.contract;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.hudini.korea.marusingle.adapter.maru.MaruAdapter;
import com.hudini.korea.marusingle.adapter.maru.contract.MaruAdapterContract;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.util.crawler.MaruUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 2018-04-05.
 */

public class MaruPresenter implements MaruContract.Presenter {
    private MaruContract.View mView;
    private MaruAdapterContract.View adapterView;
    private MaruAdapterContract.Presenter adapterPresenter;
    private Context context;
    private MaruItem maruItem;
    public MaruPresenter(MaruContract.View view, MaruAdapter adapter, MaruItem maruItem){
        this.mView = view;
        this.adapterView = adapter;
        this.adapterPresenter = adapter;
        this.context = (Context)view;
        this.maruItem = maruItem;
        mView.setPresenter(this);
    }
    @Override
    public void start() {
        MaruContentTask task = new MaruContentTask();
        task.execute();
        adapterView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mView.goToViewer(adapterPresenter.getItem(position));
            }
        });
        mView.setInitViews(maruItem);
    }

    @Override
    public void reverseSort() {
        adapterPresenter.reverseSort();
        adapterView.refresh();
    }

    private class MaruContentTask extends AsyncTask<Void, Void, Void>{
        private List<MaruContentItem> contentItemList;
        MaruContentTask(){
            contentItemList = new ArrayList<>();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mView.progressShow();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contentItemList.addAll(MaruUtil.getInstance().searchUid(maruItem.getuId()));
            Log.d("contentItems","size : "+contentItemList.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapterPresenter.addAll(contentItemList);
            adapterView.refresh();
            mView.setSpinnerView(contentItemList);
            mView.progressClose();
            super.onPostExecute(aVoid);
        }
    }
}
