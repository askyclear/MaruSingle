package com.hudini.korea.marusingle.adapter.maru;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.maru.contract.MaruAdapterContract;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-04-04.
 */

public class MaruAdapter extends RecyclerView.Adapter<MaruAdapter.ViewHolder>
                        implements MaruAdapterContract.View,MaruAdapterContract.Presenter{
    private Context context;
    private List<MaruContentItem> contentItems;
    private OnItemClickListener listener;
    public MaruAdapter(Context context) {
        this.context = context;
        this.contentItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mangalist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(contentItems.get(position).getChapterTitle());
    }

    @Override
    public int getItemCount() {
        return contentItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.manga_list_tv)
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ViewHolder.this,itemView);
        }
        @OnClick(R.id.manga_list_tv)
        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }
    //adapter view

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //adapter presenter

    @Override
    public void add(MaruContentItem maruContentItem) {
        contentItems.add(maruContentItem);
    }

    @Override
    public void addAll(List<MaruContentItem> contentItems) {
        this.contentItems.addAll(contentItems);
    }

    @Override
    public MaruContentItem remove(int position) {
        return contentItems.remove(position);
    }

    @Override
    public MaruContentItem getItem(int position) {
        return contentItems.get(position);
    }

    @Override
    public void reverseSort() {
        Collections.reverse(contentItems);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
