package com.kalei.fartgames.activities;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SplashFragment;
import com.kalei.fartgames.utils.IntentGenerator;

import android.os.Bundle;

public class SplashActivity extends FartActivity {
    InterstitialAd mInterstitialAd;
    public boolean skipAd = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));

        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container, SplashFragment.newInstance()).commit();
        FartApplication.getInstance().onSplashInitialized();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onAdFailedToLoad(final int errorCode) {
                super.onAdFailedToLoad(errorCode);
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
//        requestNewInterstitial();

    }

    //        final com.amazon.device.ads.InterstitialAd interstitialAd = new com.amazon.device.ads.InterstitialAd(this);

    // Set the listener to use the callbacks below.
//        interstitialAd.setListener(new com.amazon.device.ads.AdListener() {
//            @Override
//            public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
//                interstitialAd.showAd();
//            }
//
//            @Override
//            public void onAdFailedToLoad(final Ad ad, final AdError adError) {
//                Log.i("fg", "ad failed: " + adError.getMessage());
//            }
//
//            @Override
//            public void onAdExpanded(final Ad ad) {
//
//            }
//
//            @Override
//            public void onAdCollapsed(final Ad ad) {
//
//            }
//
//            @Override
//            public void onAdDismissed(final Ad ad) {
//                skipAd = true;
//                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            }
//        });
//
//        // Load the interstitial.
//        interstitialAd.loadAd();
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                if (skipAd) {
//                    startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                }
//            }
//        }, 6000);
//    }
}
