package com.kalei.fartgames.activities;

import com.google.android.gms.ads.AdRequest;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SplashFragment;
import com.kalei.fartgames.utils.IntentGenerator;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleNativeAd;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import static com.vungle.warren.Vungle.getNativeAd;
import static com.vungle.warren.Vungle.init;
import static com.vungle.warren.Vungle.loadAd;

public class SplashActivity extends FartActivity {
    //    InterstitialAd mInterstitialAd;
    public boolean skipAd = false;
    private final List<String> placement_collection = Arrays.asList("INTERSTITIAL-5569808");
    public RelativeLayout flex_feed;
    private final LoadAdCallback vungleLoadAdCallback = new LoadAdCallback() {
        @Override
        public void onAdLoad(String s) {

            final String placementReferenceID = s;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    VungleNativeAd nativeAd = getNativeAd(placement_collection.get(0), null);
                    flex_feed.addView(nativeAd.renderNativeView());
                }
            });
        }

        @Override
        public void onError(final String s, final Throwable throwable) {
            int x = 9;
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        flex_feed = (RelativeLayout) findViewById(R.id.flex_feed);
        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container, SplashFragment.newInstance()).commit();
        FartApplication.getInstance().onSplashInitialized();

        init(placement_collection, "5b09030a7c969d375ad2e812", this.getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                // Initialization has succeeded and SDK is ready to load an ad or play one if there
                // is one pre-cached already
                renderAd(placement_collection.get(0));
            }

            @Override
            public void onError(Throwable throwable) {
                // Initialization error occurred - throwable.getLocalizedMessage() contains error message
                int x = 9;
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {
                // Callback to notify when an ad becomes available for the auto-cached placement
                //
                // NOTE: This callback works only for the auto-cached placement. Otherwise, please use
                // LoadAdCallback with loadAd API for loading placements.
                int x = 9;
                renderAd(placementId);
            }
        });
        if (Vungle.isInitialized()) {
            loadAd(placement_collection.get(0), vungleLoadAdCallback);
        }

//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            }
//
//            @Override
//            public void onAdFailedToLoad(final int errorCode) {
//                super.onAdFailedToLoad(errorCode);
//                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                mInterstitialAd.show();
//            }
//        });
    }

    private void renderAd(String id) {
        if (Vungle.isInitialized() && Vungle.canPlayAd(id)) {
            Vungle.playAd(placement_collection.get(0), null, vunglePlayAdCallback);
//        Vungle.playAd(placement_collection.get(0), null, null);
        }
    }

    private final PlayAdCallback vunglePlayAdCallback = new PlayAdCallback() {
        @Override
        public void onAdStart(final String placementReferenceID) {
            int x = 98;
        }

        @Override
        public void onAdEnd(final String placementReferenceID, final boolean completed, final boolean isCTAClicked) {
            int x = 98;
            startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        @Override
        public void onError(final String placementReferenceID, Throwable throwable) {
            int x = 98;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

//        mInterstitialAd.loadAd(adRequest);
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
