package com.ddlab.diditalk.data.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddlab.diditalk.R;
import com.ddlab.diditalk.data.Receiver;

/**
 * Created by brunosong on 5/1/16.
 */
public class ReceiverViewHolder extends RecyclerView.ViewHolder {
    private final TextView receiverName;
    private final ImageView receiverProfile;

    public ReceiverViewHolder(View itemView) {
        super(itemView);
        receiverName = (TextView) itemView.findViewById(R.id.text_receiver_name);
        receiverProfile = (ImageView) itemView.findViewById(R.id.img_receiver_profile);
    }

    public void setReceiver(Receiver r) {
        receiverName.setText(r.getName());
        receiverProfile.setImageDrawable(r.getYourProfile());
    }
}
