package com.hudini.korea.marusingle.adapter.select;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.select.contract.SelectTagAdapterContract;
import com.hudini.korea.marusingle.constant.MaruTag;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruTagItem;
import com.hudini.korea.marusingle.util.crawler.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-04-02.
 * SelectTag에 대한 RecyclerView Adpater이다.
 */

public class SelectTagAdapter extends RecyclerView.Adapter<SelectTagAdapter.ViewHolder>
        implements SelectTagAdapterContract.View, SelectTagAdapterContract.Presenter{
    private Context context;
    private List<MaruTagItem> adpaterList;
    private OnItemClickListener onItemClickListener;
    public SelectTagAdapter(Context context) {
        this.context = context;
        adpaterList = new ArrayList<>();
    }
    @NonNull
    @Override
    public SelectTagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_taglist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTagAdapter.ViewHolder holder, int position) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Point point = DisplayUtil.containerSize(context);
        params.width = point.x / 4;
        params.height = point.x / 4;
        holder.container.setLayoutParams(params);
        holder.textView.setTextColor(Color.BLACK);
        if((position/4)%2==0&&position%2==0||(position/4)%2==1&&position%2==1) {
            if(adpaterList.get(position).isChecked()){
                holder.container.setBackgroundColor(Color.BLUE);
                holder.textView.setTextColor(Color.WHITE);
            }else
                holder.container.setBackgroundColor(Color.WHITE);
        }else{
            if(adpaterList.get(position).isChecked()){
                holder.container.setBackgroundColor(Color.BLUE);
                holder.container.setAlpha(0.7f);
                holder.textView.setTextColor(Color.WHITE);
            }else
                holder.container.setBackgroundColor(Color.GRAY);
        }
        holder.checkBox.setChecked(adpaterList.get(position).isChecked());
        if(adpaterList.get(position).getTag().equals(MaruTag.DEFAULT))
            holder.textView.setText("전체 보기");
        else
            holder.textView.setText(adpaterList.get(position).getTag().toString());

    }

    @Override
    public int getItemCount() {
        return adpaterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.select_list_tv)
        TextView textView;
        @BindView(R.id.select_list_cb)
        CheckBox checkBox;
        @BindView(R.id.select_list_container)
        FrameLayout container;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @OnClick(R.id.select_list_container)
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.select_list_container :
                        onItemClickListener.onClick(getAdapterPosition());
                break;
            }
        }
    }
    //View

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    //Presenter


    @Override
    public void add(MaruTagItem maruTagItem) {
        adpaterList.add(maruTagItem);
    }

    @Override
    public MaruTagItem remove(int position) {
        return adpaterList.remove(position);
    }

    @Override
    public MaruTagItem getItem(int position) {
        return adpaterList.get(position);
    }

    @Override
    public int getSize() {
        return adpaterList.size();
    }

    @Override
    public void clear() {
        adpaterList.clear();
    }

    @Override
    public void chageItems(List<MaruTagItem> tags) {
        clear();
        adpaterList.addAll(tags);
    }

    @Override
    public void chageItem(MaruTagItem tag, int position) {
        adpaterList.set(position,tag);
    }
}
