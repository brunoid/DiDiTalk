package com.ddlab.diditalk.net;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by brunosong on 5/1/16.
 */
public class Client {
    private static final String TAG = "ClientTag";
    private Socket socket;
    private String ip;
    public int port;

    private OutputStream socketOutput;
    private BufferedReader socketInput;
    private ClientCallback mListener;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
                    socket.connect(socketAddress);
//                    socketOutput = socket.getOutputStream();
                    socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    new ReceiveThread().start();

                    if (mListener != null) {
                        mListener.onConnect(socket);
                        Log.d(TAG, "run: 접속시도");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    if (mListener != null) {
                        mListener.onConnectError(socket, e.getMessage());
                    }
                }
            }
        }).start();
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (mListener != null)
                mListener.onDisconnect(socket, e.getMessage());
        }
    }

    public void send(String message) {
        try {
            socketOutput = socket.getOutputStream();
            socketOutput.write(message.getBytes());
            socketOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onDisconnect(socket, e.getMessage());
            }
        }
    }

    private class ReceiveThread extends Thread implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = socketInput.readLine()) != null) {
                    if (mListener != null) {
                        mListener.onMessage(message);
                    }
                }
            } catch (IOException e) {
                if (mListener != null) {
                    mListener.onDisconnect(socket, e.getMessage());
                }
            }
        }
    }

    public void setClientCallback(ClientCallback listener) {
        mListener = listener;
    }

    public void removeClientCallback() {
        this.mListener = null;
    }

    public interface ClientCallback {
        void onMessage(String message);

        void onConnect(Socket socket);

        void onDisconnect(Socket socket, String message);

        void onConnectError(Socket socket, String message);
    }

}
