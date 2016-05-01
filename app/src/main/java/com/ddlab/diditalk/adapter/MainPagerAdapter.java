package com.ddlab.diditalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ddlab.diditalk.activity.ChatListFragment;
import com.ddlab.diditalk.activity.FriendListFragment;
import com.ddlab.diditalk.activity.MoreFragment;

/**
 * Created by brunosong on 4/30/16.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return FriendListFragment.newInstance();
            case 1:
                return ChatListFragment.newInstance();

            default:
                return MoreFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab" + position;

    }
}
