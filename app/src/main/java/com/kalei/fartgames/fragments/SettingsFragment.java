package com.kalei.fartgames.fragments;

import com.kalei.fartgames.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by risaki on 2/15/16.
 */
public class SettingsFragment extends FartFragment {

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        return rootView;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            mMenuActivityListener = (IMenuActivityListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + "must implement the IMenuActivityListener");
//        }
//    }

//    @Override
//    public void onClick(final View v) {
//        switch (v.getId()) {
//            case R.id.play_button:
//                mMenuActivityListener.onPlayButtonClicked();
//                break;
//            case R.id.share_btn:
//                mMenuActivityListener.onShareButtonClicked();
//                break;
//            case R.id.settings_button:
//                mMenuActivityListener.onSettingsButtonClicked();
//                break;
//        }
//    }
}

