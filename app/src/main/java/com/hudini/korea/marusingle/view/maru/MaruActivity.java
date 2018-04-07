package com.hudini.korea.marusingle.view.maru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.maru.MaruAdapter;
import com.hudini.korea.marusingle.adapter.maru.MaruSpinnerAdapter;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.view.maru.contract.MaruContract;
import com.hudini.korea.marusingle.view.maru.contract.MaruPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-03-22.
 */

public class MaruActivity extends AppCompatActivity implements MaruContract.View, View.OnClickListener {
    @BindView(R.id.maru_back)
    ImageButton maruBack;
    @BindView(R.id.maru_toolbar)
    Toolbar toolbar;
    @BindView(R.id.maru_toolbar_title)
    TextView maruToolbarTitle;
    @BindView(R.id.maru_thumnail)
    ImageView maru_thumnail;
    @BindView(R.id.maru_list)
    RecyclerView maruRecycler;
    @BindView(R.id.maru_title)
    TextView maruTitle;
    @BindView(R.id.maru_tags)
    TextView maruTags;
    @BindView(R.id.maru_spinner)
    Spinner maruSpinner;
    @BindView(R.id.maru_bookmark)
    ImageView maruBookmark;
    @BindView(R.id.maru_list_change)
    Button maruListChangeBt;
    private MaruContract.Presenter presenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maru);
        ButterKnife.bind(this);
        //이전 엑티비티에서 intent로 보낸 정보를 가져와서 view에 셋팅
        Intent intent = this.getIntent();
        long uId = intent.getLongExtra("uId",0);
        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");
        String thumnail =intent.getStringExtra("thumnail");
        MaruItem maruItem = new MaruItem(uId,title,type,thumnail,null);
        //actionbar를 셋팅함.
        setSupportActionBar(toolbar);
        //RecyclerView init

        MaruAdapter mAdpater = new MaruAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        maruRecycler.setLayoutManager(layoutManager);
        maruRecycler.setAdapter(mAdpater);

        new MaruPresenter(this,mAdpater,maruItem);
        presenter.start();
    }

    @OnClick({R.id.maru_back,R.id.maru_bookmark,R.id.maru_go,R.id.maru_list_change})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.maru_back:
                finish();
                break;
            case R.id.maru_bookmark:
                break;
            case R.id.maru_go :
                if(maruSpinner.getSelectedItem() instanceof MaruContentItem) {
                    MaruContentItem contentItem = (MaruContentItem) maruSpinner.getSelectedItem();
                    Toast.makeText(this,contentItem.getChapterTitle() + "\n" + contentItem.getChapterUrl(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.maru_list_change :
                presenter.reverseSort();
                if(maruListChangeBt.getText().toString().equals(""))
                break;
        }
    }


    @Override
    public void setPresenter(MaruContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void setInitViews(MaruItem maruItem){
        Glide.with(this).load(maruItem.getThumnail_url()).into(maru_thumnail);
        maruTitle.setText(maruItem.getTitle());
        maruToolbarTitle.setText(maruItem.getTitle());
        maruToolbarTitle.setTextColor(Color.WHITE);
        maruTags.setText(maruItem.getType());
    }

    @Override
    public void setSpinnerView(List<MaruContentItem> contentItemList) {
        List<MaruContentItem> spinnerItems = contentItemList;
        Collections.reverse(spinnerItems);
        MaruSpinnerAdapter spinnerAdapter = new MaruSpinnerAdapter(this,R.layout.item_mangalist, spinnerItems);
        maruSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void goToViewer(MaruContentItem item) {
        Toast.makeText(this,item.getChapterTitle() + "\n" + item.getChapterUrl(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progressShow() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");
        progressDialog.show();
    }

    @Override
    public void progressClose() {
        progressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
