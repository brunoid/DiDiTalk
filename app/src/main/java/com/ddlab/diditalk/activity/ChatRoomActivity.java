package com.ddlab.diditalk.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddlab.diditalk.net.Client;
import com.ddlab.diditalk.R;
import com.ddlab.diditalk.data.Receiver;
import com.ddlab.diditalk.data.Sender;
import com.ddlab.diditalk.adapter.ChatroomRecyclerAdapter;

import java.net.Socket;

public class ChatRoomActivity extends AppCompatActivity {

    public static final String host = "192.168.0.39";
    public static final int port = 7878;
    private static final String TAG = "ChatsThreadrun";
    ChatroomRecyclerAdapter mAdapter;
    private EditText userInput;
    private Button sendBtn;
    private RecyclerView recyclerView;
    private Client client;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        userInput = (EditText) findViewById(R.id.messageInput);
        mAdapter = new ChatroomRecyclerAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChatRoomActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        client = new Client(host, port);
        client.setClientCallback(new Client.ClientCallback() {

            @Override
            public void onMessage(final String message) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Receiver r = new Receiver();
                        r.setName(message);
                        r.setYourProfile(ContextCompat.getDrawable(ChatRoomActivity.this, R.mipmap.ic_launcher));
                        mAdapter.add(r);
                        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    }
                });
            }

            @Override
            public void onConnect(Socket socket) {
                client.send("I'm droid\n");
            }

            @Override
            public void onDisconnect(Socket socket, String message) {
//                client.connect();
            }

            @Override
            public void onConnectError(Socket socket, String message) {
                Log.d(TAG, "onConnectError: " + message);
            }

        });
        client.connect();

        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = userInput.getText().toString();
                Sender s = new Sender();
                s.setName(msg);
                s.setMyProfile(ContextCompat.getDrawable(ChatRoomActivity.this, R.mipmap.ic_launcher));
                mAdapter.add(s);
                recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                client.send(msg+"\n");
                userInput.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(ChatRoomActivity.this, "OnResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.disconnect();

    }


}
































