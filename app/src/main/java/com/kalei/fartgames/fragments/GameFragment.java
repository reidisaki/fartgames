package com.kalei.fartgames.fragments;

import com.flurry.android.FlurryAgent;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.interfaces.activities.IGameActivityListener;
import com.kalei.fartgames.models.Fart;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by risaki on 2/14/16.
 */
public class GameFragment extends FartFragment implements OnClickListener {

    public IGameActivityListener mGameActivityListener;
    public Button mPlayButton, mRealButton, mFakeButton, mTryAgainButton, mShareButton;
    public TextView mGameTitleText, mScoreText, mGameProgressText, mFunnyMessage;
    private int mDisplayQuestionNumber, mNumberCorrect;
    private boolean mIsCorrect;
    public LinearLayout mGameLayout, mScoreLayout;

    public static GameFragment newInstance(int questionNumber, boolean isCorrect) {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("question", questionNumber);
        bundle.putBoolean("isCorrect", isCorrect);
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
            mIsCorrect = args.getBoolean("isCorrect", false);
            Log.i("Reid", "mCorrect is : " + mIsCorrect);
        }
        FlurryAgent.onStartSession(getActivity());
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
        mGameProgressText = (TextView) rootView.findViewById(R.id.game_progress_text);
        mGameTitleText.setText(String.format("Question #%s", mDisplayQuestionNumber));
        mScoreLayout = (LinearLayout) rootView.findViewById(R.id.score_layout);
        mGameLayout = (LinearLayout) rootView.findViewById(R.id.game_layout);
        mScoreText = (TextView) rootView.findViewById(R.id.score_text);
        mFunnyMessage = (TextView) rootView.findViewById(R.id.funny_message_text);
        mTryAgainButton = (Button) rootView.findViewById(R.id.try_again_btn);
        mShareButton = (Button) rootView.findViewById(R.id.share_btn);
        mShareButton.setOnClickListener(this);
        mTryAgainButton.setOnClickListener(this);

        if (mDisplayQuestionNumber > 1) {
            mGameProgressText.setText(Html.fromHtml(displayProgressText()));
        }

        ShowcaseView v = new ShowcaseView.Builder(getActivity())
                .setStyle(R.style.ShowcaseViewEdited)
                .setTarget(new ViewTarget(mPlayButton))
                .singleShot(1)
                .setContentTitle(getActivity().getResources().getString(R.string.click_play)).build();

        v.overrideButtonClick(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    ((View) view.getParent()).setVisibility(View.GONE);
                } catch (ClassCastException e) {
                    // In case if item is the top most view
                }
                Button b = (Button) view.findViewById(R.id.showcase_button);
                b.setVisibility(View.GONE);

//                ShowcaseView v = new ShowcaseView.Builder(getActivity())
//                        .setStyle(R.style.ShowcaseViewEdited)
//                        .setTarget(new ViewTarget(mFakeButton))
//                        .setContentTitle(getActivity().getResources().getString(R.string.click_fake)).build();
//                v.overrideButtonClick(new OnClickListener() {
//                    @Override
//                    public void onClick(final View view) {
//                        try {
//                            ((View) view.getParent()).setVisibility(View.GONE);
//                        } catch (ClassCastException e) {
//                            // In case if item is the top most view
//                        }
//                        Button b = (Button) view.findViewById(R.id.showcase_button);
//                        b.setVisibility(View.GONE);
//
//                        ShowcaseView v = new ShowcaseView.Builder(getActivity())
//                                .setStyle(R.style.ShowcaseViewEdited)
//                                .setTarget(new ViewTarget(mRealButton))
//                                .setContentTitle(getActivity().getResources().getString(R.string.click_real)).build();
//
//                        v.overrideButtonClick(new OnClickListener() {
//                            @Override
//                            public void onClick(final View view) {
//                                try {
//                                    ((View) view.getParent()).setVisibility(View.GONE);
//                                } catch (ClassCastException e) {
//                                    // In case if item is the top most view
//                                }
//                                Button b = (Button) view.findViewById(R.id.showcase_button);
//                                b.setVisibility(View.GONE);
//                            }
//                        });
//                    }
//                });
            }
        });

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
            case R.id.share_btn:
                mGameActivityListener.onShareClicked(mNumberCorrect);
                break;
            case R.id.try_again_btn:
                mScoreLayout.setVisibility(View.GONE);
                mGameLayout.setVisibility(View.GONE);
                mGameActivityListener.onTryAgainClicked();
                break;
        }
    }

    private String displayProgressText() {
        return (mIsCorrect ? "You got it <font color='#13B765'>Right!</font>" : "You got it <font color='#E23939'>Wrong!</font>");
    }

    public void displayScore() {
        mGameLayout.setVisibility(View.GONE);
        mScoreLayout.setVisibility(View.VISIBLE);
        mGameProgressText.setText("");
        mScoreText.setText(Html.fromHtml(calculateScore()));
        mFunnyMessage.setTextSize(26);
        mFunnyMessage.setText(Html.fromHtml(getFunnyMessage()));
        Log.i("Reid", "product: " + Build.PRODUCT + " model: " + Build.MODEL + " device: " + Build.DEVICE + " total Number Right : " + mNumberCorrect +
                " out of " + mDisplayQuestionNumber);
        FlurryAgent.logEvent(
                "Final Score: " + "product: " + Build.PRODUCT + " model: " + Build.MODEL + " device: " + Build.DEVICE + " total Number Right : " +
                        mNumberCorrect + " out of " + mDisplayQuestionNumber);
    }

    private String getFunnyMessage() {
        String outputString = "";
        double ratioCorrect = (double) mNumberCorrect / mDisplayQuestionNumber;
        if (ratioCorrect >= .3 && ratioCorrect < .5) {
            outputString = "<i>not bad.. ok no, it's <b>TERRIBLE</b></i>";
        }
        if (mNumberCorrect == mDisplayQuestionNumber) {
            outputString = "<b><i>AMAZING</i></b>";
        }
        if (ratioCorrect >= .5 && ratioCorrect < .8) {
            outputString = "<b><i>EHH...</i></b>";
        }
        if (ratioCorrect >= .8 && ratioCorrect < .99) {
            outputString = "<b><i>ALMOST THERE!</i></b>";
        }
        if (ratioCorrect < .3) {
            outputString = "<b><i>IS THE VOLUME ON?</i></b>";
        }
        return outputString;
    }

    private String calculateScore() {
        String scoreString;
        mNumberCorrect = 0;
        for (Fart f : FartApplication.getInstance().getGameFartList()) {
            if (f.isMarkedCorrect()) {
                mNumberCorrect++;
            }
        }
        scoreString = String.format("You got <b>%s</b> out of <b>%s</b> questions correct!", mNumberCorrect, mDisplayQuestionNumber);

        return scoreString;
    }

    @Override
    public void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(getActivity());
    }
}
