package com.ddlab.diditalk;



import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import android.provider.ContactsContract.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    EditText searchView;
    SimpleCursorAdapter mAdapter;

    String[] projection = {Contacts._ID,Contacts.DISPLAY_NAME,};
    String selection = "(("+ Contacts.DISPLAY_NAME + " NOT NULL) AND (" +
            Contacts.DISPLAY_NAME + " != ''))";
    String sortOrder = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";


    public FriendListFragment() {
        // Required empty public constructor
    }

    public static FriendListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FriendListFragment fragment = new FriendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);
        searchView = (EditText) view.findViewById(R.id.edit_search);
        listView = (ListView) view.findViewById(R.id.friend_list);

        String[] from = {Contacts.DISPLAY_NAME};
        int[] to = {android.R.id.text1};
        mAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, null, from, to, 0);

        listView.setAdapter(mAdapter);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString();
                Bundle b = new Bundle();
                b.putString("keyword", keyword);
                getLoaderManager().restartLoader(0, b, FriendListFragment.this);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        getLoaderManager().initLoader(0, null, this);
        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Contacts.CONTENT_URI;
        if (args != null) {
            String keyword = args.getString("keyword");
            if (!TextUtils.isEmpty(keyword)) {
                uri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(keyword));
            }
        }
        return new CursorLoader(getContext(), uri, projection, selection, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }
}
