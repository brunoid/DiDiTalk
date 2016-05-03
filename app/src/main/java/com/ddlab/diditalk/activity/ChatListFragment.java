package com.ddlab.diditalk.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ddlab.diditalk.data.Friend;
import com.ddlab.diditalk.R;
import com.ddlab.diditalk.adapter.ChatListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    RecyclerView chatListView;
    ChatListAdapter mAdapter;

    private FloatingActionButton fab;

    public ChatListFragment() {
        // Required empty public constructor
    }

    public static ChatListFragment newInstance() {

        Bundle args = new Bundle();

        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        setHasOptionsMenu(true);

        chatListView = (RecyclerView) view.findViewById(R.id.chat_listview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new ChatListAdapter();

        chatListView.setAdapter(mAdapter);
        chatListView.setLayoutManager(layoutManager);
        mAdapter.setOnItemClickListener(new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.btn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        initData();
        return view;
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            Friend f = new Friend();
            f.setName("박희빈" + i);
            f.setChatLastMsg("클릭하시면 다른 안드폰으로 접속하신분과 대화할수 있습니다~");
            f.setPhoto(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher));
            mAdapter.add(f);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_chatlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more:
                Toast.makeText(getContext(), "Setting page Coming soon", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
