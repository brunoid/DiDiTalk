package com.ddlab.diditalk;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heedong on 16. 5. 1..
 */
public class ChatListAdapter extends  RecyclerView.Adapter<ChatlistViewHolder> {
    List<Friend> items = new ArrayList<Friend>();

    public void add(Friend friend){
        items.add(friend);
        notifyDataSetChanged();
    }

    @Override
    public ChatlistViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_list, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mListener.onItemClick();
            }
        });
        ChatlistViewHolder vHolder = new ChatlistViewHolder(view);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(ChatlistViewHolder holder, int position) {
        holder.setChatlist(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(Friend f);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
