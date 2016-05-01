package com.ddlab.diditalk.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddlab.diditalk.adapter.ui.ChatlistViewHolder;
import com.ddlab.diditalk.data.Friend;
import com.ddlab.diditalk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heedong on 16. 5. 1..
 */
public class ChatListAdapter extends  RecyclerView.Adapter<ChatlistViewHolder> {
    List<Friend> items = new ArrayList<Friend>();
    private OnItemClickListener mListener;

    public void add(Friend friend){
        items.add(friend);
        notifyDataSetChanged();
    }

    @Override
    public ChatlistViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_list, parent, false);
        return new ChatlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChatlistViewHolder holder, final int position) {
        holder.setChatlist(items.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




}
