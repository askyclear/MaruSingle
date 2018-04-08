package com.hudini.korea.marusingle.view.search.contract;

import android.os.AsyncTask;

import com.hudini.korea.marusingle.adapter.main.MainAdapter;
import com.hudini.korea.marusingle.adapter.main.contract.MainAdapterContract;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.util.crawler.MaruUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korea on 2018-04-08.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private MainAdapterContract.View adapterView;
    private MainAdapterContract.Presenter adapterPresenter;
    public SearchPresenter(SearchContract.View view, MainAdapter adapter) {
        this.mView = view;
        this.adapterView = adapter;
        this.adapterPresenter = adapter;
        this.adapterView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mView.goToMaruActivity(adapterPresenter.getItem(position));
            }
        });
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setInitView();
    }

    @Override
    public void searchKeyword(String keyword) {
        SearchAsyncTask task = new SearchAsyncTask();
        task.execute(keyword);
    }
    private class SearchAsyncTask extends AsyncTask<String,Void,Void>{
        private List<MaruItem> maruItemList;

        public SearchAsyncTask() {
            maruItemList = new ArrayList<>();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mView.showProgress();
        }

        @Override
        protected Void doInBackground(String... strings) {
            MaruUtil utils = MaruUtil.getInstance();
            maruItemList.addAll(utils.searchKeyWord(strings[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterPresenter.clear();
            adapterPresenter.addList(maruItemList);
            adapterView.refresh();
            mView.dismissProgress();
        }
    }
}
