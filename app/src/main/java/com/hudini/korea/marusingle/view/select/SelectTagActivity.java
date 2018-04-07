package com.hudini.korea.marusingle.view.select;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.select.SelectTagAdapter;
import com.hudini.korea.marusingle.view.select.contract.SelectTagContract;
import com.hudini.korea.marusingle.view.select.contract.SelectTagPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-04-02.
 */

public class SelectTagActivity extends AppCompatActivity implements View.OnClickListener,SelectTagContract.View{
    private SelectTagContract.Presenter presenter;
    @BindView(R.id.select_toolbar)
    Toolbar toolbar;
//    @BindView(R.id.select_back)
//    ImageButton selectBack;
    @BindView(R.id.select_tags_recycler)
    RecyclerView recyclerView;
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tag);
        ButterKnife.bind(this);
        //set Toolbar
        setSupportActionBar(toolbar);
        //set Recyclerview
        SelectTagAdapter adapter = new SelectTagAdapter(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new SelectTagPresenter(this,adapter);
        presenter.start();
    }

    @OnClick({R.id.select_back})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_back :
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(SelectTagContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
