package com.kalei.fartgames.fragments;

import com.kalei.fartgames.R;
import com.kalei.fartgames.interfaces.activities.IGameActivityListener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by risaki on 2/14/16.
 */
public class GameFragment extends FartFragment implements OnClickListener {

    public IGameActivityListener mGameActivityListener;
    public Button mPlayButton, mRealButton, mFakeButton;
    public TextView mGameTitleText;
    public int mDisplayQuestionNumber;

    public static GameFragment newInstance(int questionNumber) {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("question", questionNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            Log.i("Reid", "args is null");
        } else {
            mDisplayQuestionNumber = args.getInt("question");
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        mPlayButton = (Button) rootView.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);
        mRealButton = (Button) rootView.findViewById(R.id.real_button);
        mRealButton.setOnClickListener(this);
        mFakeButton = (Button) rootView.findViewById(R.id.fake_button);
        mFakeButton.setOnClickListener(this);
        mGameTitleText = (TextView) rootView.findViewById(R.id.game_title_text);
        mGameTitleText.setText(String.format("Question #%s", mDisplayQuestionNumber));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mGameActivityListener = (IGameActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement the IGameActivityListener");
        }
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.play_button:
                mGameActivityListener.onPlayButtonClicked();
                break;
            case R.id.fake_button:
                mGameActivityListener.onFakeButtonClicked();
                break;
            case R.id.real_button:
                mGameActivityListener.onRealButtonClicked();
                break;
        }
    }
}
