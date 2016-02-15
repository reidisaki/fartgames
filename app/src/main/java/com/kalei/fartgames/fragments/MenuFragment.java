package com.kalei.fartgames.fragments;

import com.kalei.fartgames.R;
import com.kalei.fartgames.interfaces.activities.IMenuActivityListener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by risaki on 2/14/16.
 */
public class MenuFragment extends FartFragment implements View.OnClickListener {
    public IMenuActivityListener mMenuActivityListener;
    public Button mPlayButton, mCustomButton, mSettingsButton;

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mPlayButton = (Button) rootView.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mMenuActivityListener = (IMenuActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement the IMenuActivityListener");
        }
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.play_button:
                mMenuActivityListener.onPlayButtonClicked();
        }
    }
}
