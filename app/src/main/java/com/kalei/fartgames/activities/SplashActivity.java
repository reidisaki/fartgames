package com.kalei.fartgames.activities;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SplashFragment;
import com.kalei.fartgames.utils.IntentGenerator;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends FartActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container, SplashFragment.newInstance()).commit();
        FartApplication.getInstance().onSplashInitialized();

        final com.amazon.device.ads.InterstitialAd interstitialAd = new com.amazon.device.ads.InterstitialAd(this);

        // Set the listener to use the callbacks below.
        interstitialAd.setListener(new com.amazon.device.ads.AdListener() {
            @Override
            public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
                interstitialAd.showAd();
            }

            @Override
            public void onAdFailedToLoad(final Ad ad, final AdError adError) {
                Log.i("fg", "ad failed: " + adError.getMessage());
            }

            @Override
            public void onAdExpanded(final Ad ad) {

            }

            @Override
            public void onAdCollapsed(final Ad ad) {

            }

            @Override
            public void onAdDismissed(final Ad ad) {
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // Load the interstitial.
        interstitialAd.loadAd();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 6000);
    }
}
