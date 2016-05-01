package com.ddlab.diditalk.data;

import android.graphics.drawable.Drawable;

import com.ddlab.diditalk.data.ChatData;

/**
 * Created by brunosong on 5/1/16.
 */
public class Sender implements ChatData {
    String name;
    Drawable myProfile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getMyProfile() {
        return myProfile;
    }

    public void setMyProfile(Drawable myProfile) {
        this.myProfile = myProfile;
    }

    @Override
    public String toString() {
        return name;
    }
}
