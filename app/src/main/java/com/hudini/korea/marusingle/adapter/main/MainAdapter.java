package com.hudini.korea.marusingle.adapter.main;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.adapter.main.contract.MainAdapterContract;
import com.hudini.korea.marusingle.listener.OnItemClickListener;
import com.hudini.korea.marusingle.model.maru.MaruItem;
import com.hudini.korea.marusingle.util.crawler.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by korea on 2018-03-26.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements
        MainAdapterContract.View, MainAdapterContract.Presenter {
    private Context context;
    private List<MaruItem> maruList;
    private OnItemClickListener listener;

    public MainAdapter(Context context) {
        this.context = context;
        maruList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_marulist, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MaruItem maruItem = getItem(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Point point = DisplayUtil.containerSize(context);
        params.height = point.y / 5;
        holder.container.setLayoutParams(params);

        Glide.with(context).load(maruItem.getThumnail_url()).into(holder.list_thumnail);
//        if (maruItem.getThumnail_bitmap() != null) {
//            holder.list_thumnail.setBackground(null);
//            holder.list_thumnail.setImageBitmap(maruItem.getThumnail_bitmap());
//        } else
//            holder.list_thumnail.setImageBitmap(null);
        holder.list_title.setTag(maruItem.getuId());
        holder.list_title.setText(maruItem.getTitle());
        holder.list_tags.setText(maruItem.getType());

    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    //adaptercontract 의 view 인터페이스 구현
    @Override
    public void refreshPosition(int position) {
        notifyItemChanged(position);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void refreshRange(int oldPosition, int size) {
        notifyItemRangeChanged(oldPosition,size);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //adaptercontract의 presenterr 인터페이스 구현

    /**
     * 마루 아이템을 현 maruList의 마지막에 추가
     *
     * @param maruItem is not null
     */
    @Override
    public void add(MaruItem maruItem) {
        maruList.add(maruItem);
    }

    /**
     * 리스트를 현 maruList에 추가함
     *
     * @param maruItems
     * @throws IndexOutOfBoundsException 이 생길수 있음
     */
    @Override
    public void addList(List<MaruItem> maruItems) {
        this.maruList.addAll(maruItems);
    }

    /**
     * @param position 은 maruList이내의 값이여야 함.
     * @param maruItem
     * @throws IndexOutOfBoundsException 이 생길수 있음
     */
    @Override
    public void setItem(int position, MaruItem maruItem) {
        maruList.set(position, maruItem);
    }

    /**
     * 포지션의 아이템을 지우고 그 값을 반환
     *
     * @param position > 0
     * @return maruItem in maruList.get(index)
     * @throws ArrayIndexOutOfBoundsException if maruList.size(() is less than position
     */
    @Override
    public MaruItem remove(int position) {
        return maruList.remove(position);
    }


    /**
     * position의 객체를 가져오기 위한 메소드
     *
     * @param position
     * @return maruList의 포지션에 있는 MaruItem 객체를 반환
     */
    @Override
    public MaruItem getItem(int position) {
        return maruList.get(position);
    }

    /**
     * @return 현 maruList의 사이즈 반환
     */
    @Override
    public int getSize() {
        return maruList.size();
    }

    /**
     * maruList의 전체 객체를 삭제함
     */
    @Override
    public void clear() {
        maruList.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.list_thumnail)
        ImageView list_thumnail;
        @BindView(R.id.list_title)
        TextView list_title;
        @BindView(R.id.list_tags)
        TextView list_tags;
        @BindView(R.id.list_container)
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.list_container)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.list_container:
                    listener.onClick(getAdapterPosition());
                    break;
            }
        }
    }

}
