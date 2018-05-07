package com.example.cpzhoucheng.monashfriendfinder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CPZHOUCHENG on 6/5/17.
 */

public class FragmentFriendsMap extends Fragment {

    View vFriendsMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vFriendsMap = inflater.inflate(R.layout.fragment_friendsmap, container, false);
        return vFriendsMap;
    }
}
