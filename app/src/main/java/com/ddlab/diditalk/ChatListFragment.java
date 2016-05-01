package com.ddlab.diditalk;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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
        initData();

        chatListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatRoomActivity.class);
                startActivity(intent);
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.btn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "comming soong Chat Room Civil War", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initData() {
        for(int i = 0; i < 40; i++){
            Friend f = new Friend();
            f.setName("박희빈"+i);
            f.setChatLastMsg("디디톡 좋아~ 디디디디디~");
            f.setPhoto(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher));
            mAdapter.add(f);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_chatlist,menu);
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
