package com.kalei.fartgames.activities;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SplashFragment;
import com.kalei.fartgames.utils.IntentGenerator;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.Window;

public class SplashActivity extends FartActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container, SplashFragment.newInstance()).commit();
        FartApplication.getInstance().onSplashInitialized();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
