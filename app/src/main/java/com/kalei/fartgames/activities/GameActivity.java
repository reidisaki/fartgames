package com.kalei.fartgames.activities;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.enums.Authenticity;
import com.kalei.fartgames.fragments.GameFragment;
import com.kalei.fartgames.interfaces.activities.IGameActivityListener;
import com.kalei.fartgames.models.Fart;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by risaki on 2/14/16.
 */
public class GameActivity extends FartActivity implements IGameActivityListener {
    MediaPlayer mPlayer;
    Fart mFart;
    int mQuestionNumber, mDisplayQuestionNumber;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionNumber = 0;
        mDisplayQuestionNumber = 1;
        setContentView(R.layout.activity_game);
        getSupportFragmentManager().beginTransaction().replace(R.id.game_container, GameFragment.newInstance(mDisplayQuestionNumber)).commit();
        setupFart();
        loadAds();
        loadToolbar(getString(R.string.game));
    }

    private void setupFart() {
        if (mFart != null) {
            //output message
            int previousQuestion = mQuestionNumber - 1;
            FartApplication.getInstance().updateFart(previousQuestion, mFart);
        }
        mFart = FartApplication.getInstance().getFart(mQuestionNumber);
        if (mFart == null) {
            Log.i("Reid", "end game");
        } else {
            Log.i("Reid", mFart.getAuthenticity().toString() + " questionNumber: " + mQuestionNumber + " id: " + mFart.getId());
        }
    }

    @Override
    public void onPlayButtonClicked() {
        //randomize this and
        MediaPlayer mPlayer = MediaPlayer.create(this, mFart.getRawId());
        mPlayer.start();
    }

    @Override
    public void onRealButtonClicked() {
        mQuestionNumber++;
        mDisplayQuestionNumber++;
        if (mFart.getAuthenticity() == Authenticity.REAL) {
            Toast.makeText(this, "you got it right!", Toast.LENGTH_SHORT).show();
            mFart.setMarkedCorrect(true);
        } else {
            Toast.makeText(this, "you got it WRONG!", Toast.LENGTH_SHORT).show();
            mFart.setMarkedCorrect(false);
        }
        //keep track of what a user said
        getSupportFragmentManager().beginTransaction().replace(R.id.game_container, GameFragment.newInstance(mDisplayQuestionNumber)).commit();
        setupFart();
        loadAds();
    }

    @Override
    public void onFakeButtonClicked() {
        mQuestionNumber++;
        mDisplayQuestionNumber++;
        if (mFart.getAuthenticity() == Authenticity.FAKE) {
            Toast.makeText(this, "you got it right!", Toast.LENGTH_SHORT).show();
            mFart.setMarkedCorrect(true);
        } else {
            Toast.makeText(this, "you got it WRONG!", Toast.LENGTH_SHORT).show();
            mFart.setMarkedCorrect(false);
        }
        //keep track of fake button clicked
        //keep track of what a user said
        getSupportFragmentManager().beginTransaction().replace(R.id.game_container, GameFragment.newInstance(mDisplayQuestionNumber)).commit();
        setupFart();
        loadAds();
    }

    public void onDestroy() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
        super.onDestroy();
    }
}
