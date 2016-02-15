package com.kalei.fartgames.activities;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.kalei.fartgames.FartApplication;
import com.kalei.fartgames.R;
import com.kalei.fartgames.utils.IntentGenerator;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        AdView mAdView = (AdView) findViewById(R.id.adView);
        if (mAdView != null) {
            String android_id = Secure.getString(this.getContentResolver(),
                    Secure.ANDROID_ID);
            AdRequest adRequest = new AdRequest.Builder().addTestDevice(android_id).build();
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
}
