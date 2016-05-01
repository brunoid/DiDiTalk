package com.ddlab.diditalk.data.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddlab.diditalk.R;
import com.ddlab.diditalk.data.Sender;

/**
 * Created by brunosong on 5/1/16.
 */
public class SenderViewHolder extends RecyclerView.ViewHolder {

    private final TextView senderName;
    private final ImageView senderProfile;

    public SenderViewHolder(View itemView) {
        super(itemView);
        senderName = (TextView) itemView.findViewById(R.id.text_myName);
        senderProfile = (ImageView) itemView.findViewById(R.id.img_sender_Profile);
    }

    public void setSender(Sender s) {
        senderName.setText(s.getName());
        senderProfile.setImageDrawable(s.getMyProfile());
    }
}
