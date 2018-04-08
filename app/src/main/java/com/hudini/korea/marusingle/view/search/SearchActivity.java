package com.hudini.korea.marusingle.view.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.main.MainAdapter;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.view.maru.MaruActivity;
import com.hudini.korea.marusingle.view.search.contract.SearchContract;
import com.hudini.korea.marusingle.view.search.contract.SearchPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-04-08.
 * 검색 엑티비티이다.
 */

public class SearchActivity extends AppCompatActivity implements SearchContract.View,View.OnClickListener{
    @BindView(R.id.search_back)
    ImageButton search_back;
    @BindView(R.id.search_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_list)
    RecyclerView recyclerView;
    @BindView(R.id.search_view)
    SearchView searchView;
    private SearchContract.Presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //search toolbar setting
        setSupportActionBar(toolbar);
        //searchView에 이벤트 리스너 등록
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchKeyword(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //searchView SubmitButton 가능하게 함
        searchView.setSubmitButtonEnabled(true);
        //Main의 리사이클러뷰와 형태가 같기에 Main Adapter를 사용함
        MainAdapter adapter = new MainAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new SearchPresenter(this, adapter);
        presenter.start();
    }
    @OnClick({R.id.search_back})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back :
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //SearchContract의 View 오버라이드

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setInitView() {
        searchView.setQueryHint("검색내용 입력");
    }
    private ProgressDialog dialog;
    @Override
    public void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Searching...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }

    @Override
    public void dismissProgress() {
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void goToMaruActivity(MaruItem maruItem) {
        Intent intent = new Intent(this, MaruActivity.class);
        intent.putExtra("uId",maruItem.getuId());
        intent.putExtra("title",maruItem.getTitle());
        intent.putExtra("type",maruItem.getType());
        intent.putExtra("thumnail",maruItem.getThumnail_url());
        startActivity(intent);
    }
}
