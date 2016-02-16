package com.kalei.fartgames.activities;

import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.SettingsFragment;

import android.os.Bundle;

/**
 * Created by risaki on 2/15/16.
 */
public class SettingsActivity extends FartActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, SettingsFragment.newInstance()).commit();

        loadToolbar(getString(R.string.action_settings));
        loadAds();
    }
}
