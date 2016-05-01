package com.ddlab.diditalk.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddlab.diditalk.R;
import com.ddlab.diditalk.data.Receiver;
import com.ddlab.diditalk.data.ui.ReceiverViewHolder;
import com.ddlab.diditalk.data.Sender;
import com.ddlab.diditalk.data.ui.SenderViewHolder;
import com.ddlab.diditalk.data.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunosong on 5/1/16.
 */
public class ChatroomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SENDER_VIEW_TYPE = 0;
    private static final int RECEIVER_VIEW_TYPE = 1;
    private static final String TAG = "AdapterCheck";

    List<ChatData> items = new ArrayList<>();

    public void add(ChatData c) {
        items.add(c);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        ChatData data = items.get(position);
        if (data instanceof Sender) {
            Log.d(TAG, "getItemViewType: sender");
            return SENDER_VIEW_TYPE;
        } else if (data instanceof Receiver) {
            Log.d(TAG, "getItemViewType: receiver");
            return RECEIVER_VIEW_TYPE;
        } else {
            return SENDER_VIEW_TYPE;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = null;
        switch (viewType) {
            case SENDER_VIEW_TYPE:
                v = inflater.inflate(R.layout.view_sender, parent, false);
                return new SenderViewHolder(v);
            default:
                v = inflater.inflate(R.layout.view_receiver, parent, false);
                return new ReceiverViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "스위치 리턴값 확인: "+ getItemViewType(position));
        switch (getItemViewType(position)) {

            case SENDER_VIEW_TYPE: {

                SenderViewHolder mHolder = (SenderViewHolder) holder;
                Log.d(TAG, "gothhere: "+ mHolder.toString());
                mHolder.setSender((Sender) items.get(position));
                break;
            }
            case RECEIVER_VIEW_TYPE: {
                ReceiverViewHolder mHolder = (ReceiverViewHolder) holder;
                mHolder.setReceiver((Receiver) items.get(position));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
