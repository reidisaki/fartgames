package com.kalei.fartgames.activities;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SplashFragment;
import com.kalei.fartgames.utils.IntentGenerator;

import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends FartActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container, SplashFragment.newInstance()).commit();
        FartApplication.getInstance().onSplashInitialized();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(IntentGenerator.getMenuActivityIntent(SplashActivity.this));
            }
        }, 2000);
    }
}
