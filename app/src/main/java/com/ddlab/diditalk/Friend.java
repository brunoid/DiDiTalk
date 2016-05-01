package com.ddlab.diditalk;

import android.graphics.drawable.Drawable;

/**
 * Created by heedong on 16. 5. 1..
 */
public class Friend {
    private String name;
    private String phoneNumber;
    private String chatLastMsg;
    private Drawable photo;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChatLastMsg() {
        return chatLastMsg;
    }

    public void setChatLastMsg(String chatLastMsg) {
        this.chatLastMsg = chatLastMsg;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

}
