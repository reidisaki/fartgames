package com.kalei.fartgames.utils;

import com.kalei.fartgames.activities.GameActivity;
import com.kalei.fartgames.activities.MenuActivity;
import com.kalei.fartgames.activities.SettingsActivity;
import com.kalei.fartgames.activities.SplashActivity;

import android.content.Context;
import android.content.Intent;

/**
 * Created by risaki on 2/14/16.
 */
public class IntentGenerator {
    public static Intent getSplashActivityIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public static Intent getMenuActivityIntent(Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        return intent;
    }

    public static Intent getGameActivityIntent(Context context) {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    public static Intent getSettingsActivityIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

//    public static Intent getDeeplinkActivityIntent(Context context, Uri deeplinkData) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(DeeplinkActivity.BUNDLE_DEEPLINK_URI, deeplinkData);
//
//        Intent intent = new Intent(context, DeeplinkActivity.class);
//        intent.putExtras(bundle);
//        return intent;
//    }
}
