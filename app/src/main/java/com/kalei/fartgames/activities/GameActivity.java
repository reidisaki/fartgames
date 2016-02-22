package com.kalei.fartgames.activities;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.enums.Authenticity;
import com.kalei.fartgames.fragments.GameFragment;
import com.kalei.fartgames.interfaces.activities.IGameActivityListener;
import com.kalei.fartgames.models.Fart;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by risaki on 2/14/16.
 */
public class GameActivity extends FartActivity implements IGameActivityListener {
    MediaPlayer mPlayer;
    Fart mFart;
    int mQuestionNumber, mDisplayQuestionNumber;
    GameFragment mGameFragment;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionNumber = 0;
        mDisplayQuestionNumber = 1;
        setContentView(R.layout.activity_game);
        mGameFragment = GameFragment.newInstance(mDisplayQuestionNumber, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.game_container, mGameFragment).commit();
        setupFart();
        loadAds();
        loadToolbar(getString(R.string.app_name));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdClosed() {

            }
        });
    }

    private void setupFart() {
        boolean isMarkedCorrect = false;
        if (mFart != null) {
            //output message
            int previousQuestion = mQuestionNumber - 1;
            isMarkedCorrect = mFart.isMarkedCorrect();
            FartApplication.getInstance().updateFart(previousQuestion, mFart);
        }
        mFart = FartApplication.getInstance().getFart(mQuestionNumber);
        if (mFart == null) {
            mGameFragment.displayScore();
        } else {
            mGameFragment = GameFragment.newInstance(mDisplayQuestionNumber, isMarkedCorrect);
            getSupportFragmentManager().beginTransaction().replace(R.id.game_container, mGameFragment).commit();
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
//            Toast.makeText(this, "you got it right!", Toast.LENGTH_SHORT).show();
            mFart.setMarkedCorrect(true);
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.correct);
            mPlayer.start();
        } else {
//            Toast.makeText(this, "you got it WRONG!", Toast.LENGTH_SHORT).show();
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.wrong);
            mPlayer.start();
            mFart.setMarkedCorrect(false);
        }

        setupFart();
        loadAds();
    }

    @Override
    public void onFakeButtonClicked() {
        mQuestionNumber++;
        mDisplayQuestionNumber++;
        if (mFart.getAuthenticity() == Authenticity.FAKE) {
//            Toast.makeText(this, "you got it right!", Toast.LENGTH_SHORT).show();
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.correct);
            mPlayer.start();
            mFart.setMarkedCorrect(true);
        } else {
//            Toast.makeText(this, "you got it WRONG!", Toast.LENGTH_SHORT).show();
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.wrong);
            mPlayer.start();
            mFart.setMarkedCorrect(false);
        }
        setupFart();
        loadAds();
    }

    @Override
    public void onTryAgainClicked() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        FartApplication.getInstance().setupGame();
        mQuestionNumber = 0;
        mDisplayQuestionNumber = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.game_container, GameFragment.newInstance(mDisplayQuestionNumber, false))
                .commit();
        setupFart();
        loadAds();
    }

    @Override
    public void onShareClicked(int numCorrect) {
        super.onShareClicked(numCorrect);
    }

    public void onDestroy() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
