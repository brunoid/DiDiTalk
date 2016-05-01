package com.ddlab.diditalk.adapter.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddlab.diditalk.data.Friend;
import com.ddlab.diditalk.R;

/**
 * Created by heedong on 16. 5. 1..
 */
public class ChatlistViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, lastmsgView;
    ImageView profileView;
    Friend friend;

    public ChatlistViewHolder(View view) {
        super(view);
        nameView = (TextView) view.findViewById(R.id.chat_name);
        lastmsgView = (TextView) view.findViewById(R.id.chat_dsc);
        profileView = (ImageView) view.findViewById(R.id.chat_profile);
    }

    public void setChatlist(Friend f) {
        friend = f;
        nameView.setText(f.getName());
        lastmsgView.setText(f.getChatLastMsg());
        profileView.setImageDrawable(f.getPhoto());
    }
}
