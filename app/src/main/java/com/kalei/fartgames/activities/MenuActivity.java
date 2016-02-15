package com.kalei.fartgames.activities;

import com.kalei.fartgames.R;
import com.kalei.fartgames.fragments.MenuFragment;
import com.kalei.fartgames.interfaces.activities.IMenuActivityListener;
import com.kalei.fartgames.utils.IntentGenerator;

import android.os.Bundle;

/**
 * Created by risaki on 2/14/16.
 */
public class MenuActivity extends FartActivity implements IMenuActivityListener {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, MenuFragment.newInstance()).commit();

        loadToolbar(getString(R.string.menu));
        loadAds();
    }

    @Override
    public void onPlayButtonClicked() {
        startActivity(IntentGenerator.getGameActivityIntent(this));
    }

    @Override
    public void onCustomButtonClicked() {

    }

    @Override
    public void onSettingsButtonClicked() {

    }
}
