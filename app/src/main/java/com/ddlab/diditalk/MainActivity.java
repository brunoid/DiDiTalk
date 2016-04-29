package com.ddlab.diditalk;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pager;
    private MainPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);

        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();
        tabs.addTab(tabs.newTab().setTag("tab1").setIcon(R.mipmap.ic_launcher));
        tabs.addTab(tabs.newTab().setTag("tab2").setIcon(R.mipmap.ic_launcher));
        tabs.addTab(tabs.newTab().setTag("tab3").setIcon(R.mipmap.ic_launcher));

    }
}
