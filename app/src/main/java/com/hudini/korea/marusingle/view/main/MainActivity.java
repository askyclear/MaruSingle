package com.hudini.korea.marusingle.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.main.MainAdapter;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.view.main.contract.MainContract;
import com.hudini.korea.marusingle.view.main.contract.MainPresenter;
import com.hudini.korea.marusingle.view.maru.MaruActivity;
import com.hudini.korea.marusingle.view.select.SelectTagActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 여기는 메인 화면으로 시작 시 마루마루 해준다.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener {
    private MainContract.Presenter presenter;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_search_go)
    ImageButton search_go;
    @BindView(R.id.main_type_go)
    ImageButton type_go;
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.main_tablayout)
    TabLayout tabLayout;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind the activity
        ButterKnife.bind(this);
        //Set up the Toolbar
        setSupportActionBar(toolbar);
        //Set up recyclerview
        MainAdapter mainAdapter = new MainAdapter(this);
        LinearLayoutManager layoutManger = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManger.findFirstVisibleItemPosition();
        recyclerView.setLayoutManager(layoutManger);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                /*dy > 0 보다 크다는 것은 아래로 스크롤링 하고 있다는 뜻,
                그리고 현재 리사이클러뷰에서 마지막에 보여주는 아이템의 포지션과 지금 현재 전체 아이템 카운터의 값이 같을경우
                다음 페이지를 가져오게 하였다.*/
                if(dy>0 &&layoutManager.findLastCompletelyVisibleItemPosition()== layoutManager.getItemCount()-1){
                    presenter.nextPage();
                }

            }
        });
        //Create the presenter
        new MainPresenter(this, mainAdapter);
        presenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.isChanged();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
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

    @OnClick({R.id.main_search_go, R.id.main_type_go})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_search_go:
                break;
            case R.id.main_type_go:
                Intent intent  = new Intent(this, SelectTagActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void progressShow() {
        progress = new ProgressDialog(this);
        progress.setMessage("Now loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public void progressClose() {
        progress.dismiss();
    }

    @Override
    protected void onDestroy() {
        presenter.clearView();
        super.onDestroy();
    }
}
