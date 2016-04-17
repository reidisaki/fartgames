package com.kalei.fartgames.activities;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.utils.IntentGenerator;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by risaki on 2/14/16.
 */
public abstract class FartActivity extends AppCompatActivity {

    //    private AdLayout adView;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
//        AdRegistration.setAppKey(getString(R.string.amazon_ad_key));
//        AdRegistration.enableLogging(true);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (!(this instanceof SplashActivity) && !FartApplication.getInstance().isSplashInitialized()) {
            startActivity(IntentGenerator.getSplashActivityIntent(this));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadAds() {
//        adView = (AdLayout) findViewById(R.id.adview);
//
//        AdTargetingOptions adOptions = new AdTargetingOptions();
//        // Optional: Set ad targeting options here.
//        adView.loadAd(adOptions); // Retrieves an ad on background thread
//
        AdView mAdView = (AdView) findViewById(R.id.adView);
        if (mAdView != null) {
            String android_id = Secure.getString(this.getContentResolver(),
                    Secure.ANDROID_ID);
//            AdRequest adRequest = new AdRequest.Builder().addTestDevice(android_id).build();
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }

    public void loadToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void onShareClicked(int numCorrect) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        if (numCorrect == -1) {
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text_generic));
        } else {
            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.share_text),
                    numCorrect, FartApplication.getInstance().getGameFartList().size()));
        }

        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    protected void requestNewInterstitial() {
//        final com.amazon.device.ads.InterstitialAd interstitialAd = new com.amazon.device.ads.InterstitialAd(this);
//
//        // Set the listener to use the callbacks below.
//        interstitialAd.setListener(new com.amazon.device.ads.AdListener() {
//            @Override
//            public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
//                interstitialAd.showAd();
//            }
//
//            @Override
//            public void onAdFailedToLoad(final Ad ad, final AdError adError) {
//                Log.i("pl", "ad failed: " + adError.getMessage());
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
//
//            }
//        });
//
//        // Load the interstitial.
//        interstitialAd.loadAd();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
