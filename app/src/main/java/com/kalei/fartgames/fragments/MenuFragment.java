package com.kalei.fartgames.fragments;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.interfaces.activities.IMenuActivityListener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by risaki on 2/14/16.
 */
public class MenuFragment extends FartFragment implements View.OnClickListener {
    public IMenuActivityListener mMenuActivityListener;
    public Button mPlayButton, mCustomButton, mSettingsButton, mShareButton;
    public TextView mVersionText;

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
        mShareButton = (Button) rootView.findViewById(R.id.share_btn);
        mShareButton.setOnClickListener(this);
        mSettingsButton = (Button) rootView.findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(this);
        mVersionText = (TextView) rootView.findViewById(R.id.version_text);
        mVersionText.setText("v " + FartApplication.getInstance().getVersionName(getActivity()));
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
                break;
            case R.id.share_btn:
                mMenuActivityListener.onShareButtonClicked();
                break;
            case R.id.settings_button:
                mMenuActivityListener.onSettingsButtonClicked();
                break;
        }
    }
}
