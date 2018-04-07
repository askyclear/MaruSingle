package com.hudini.korea.marusingle.adapter.maru;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hudini.korea.marusingle.R;
import com.hudini.korea.marusingle.model.maru.MaruContentItem;

import java.util.List;

/**
 * Created by korea on 2018-04-05.
 */

public class MaruSpinnerAdapter extends ArrayAdapter<MaruContentItem> {
    private List<MaruContentItem> contentItems;
    private Context context;
    private int textViewResourceId;
    public MaruSpinnerAdapter(Context context,int textViewResourceId,List<MaruContentItem> contentItems){
        super(context,textViewResourceId,contentItems);
        this.context =context;
        this.contentItems = contentItems;
        this.textViewResourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(context).inflate(textViewResourceId,parent,false);
        }
        TextView textView = (TextView)view.findViewById(R.id.manga_list_tv);
        textView.setText(contentItems.get(position).getChapterTitle());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(context).inflate(textViewResourceId,parent,false);
        }
        TextView textView = (TextView)view.findViewById(R.id.manga_list_tv);
        textView.setText(contentItems.get(position).getChapterTitle());
        return view;
    }
}
