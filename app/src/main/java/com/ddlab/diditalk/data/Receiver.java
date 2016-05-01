package com.ddlab.diditalk.data;

import android.graphics.drawable.Drawable;

import com.ddlab.diditalk.data.ChatData;

/**
 * Created by brunosong on 5/1/16.
 */
public class Receiver implements ChatData {
    String name;
    Drawable yourProfile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getYourProfile() {
        return yourProfile;
    }

    public void setYourProfile(Drawable yourProfile) {
        this.yourProfile = yourProfile;
    }

    @Override
    public String toString() {
        return name;
    }
}
