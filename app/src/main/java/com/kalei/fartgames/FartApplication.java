package com.kalei.fartgames;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.flurry.android.FlurryAgent;
import com.kalei.fartgames.models.Fart;
import com.kalei.fartgames.utils.FartGenerator;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

/**
 * Created by risaki on 2/14/16.
 */
public class FartApplication extends Application {
    private static FartApplication mInstance;
    private static ArrayList<Fart> sFartList;
    private static ArrayList<Fart> sGameFartList;
    private boolean mIsSplashInitialized = false;
    private static int TOTAL_QUESTIONS = 10;
    private static int TOTAL_FARTS = 5;

    private static String FLURRY_KEY = "32PG2RYZ3GNG6SRBD75Q";

    public FartApplication() {
        mInstance = this;
    }

    public static FartApplication getInstance() {
        return mInstance;
    }

    public void onSplashInitialized() {
        mIsSplashInitialized = true;
    }

    public boolean isSplashInitialized() {
        return mIsSplashInitialized;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(true).build())
                .build();
        // configure Flurry
        FlurryAgent.setLogEnabled(false);

        // init Flurry
        FlurryAgent.init(this, FLURRY_KEY);
        mIsSplashInitialized = false;
        setupGame();
    }

    public void setupGame() {
        sFartList = new ArrayList<>();
        TOTAL_FARTS = FartGenerator.numberOfSounds();
        FartGenerator.loadFarts(sFartList, this);
        sGameFartList = FartGenerator.shuffleFarts(sFartList, TOTAL_QUESTIONS);
    }

    public Fart getFart(int index) {
        if (index >= sGameFartList.size()) {
            return null;
        } else {
            return sGameFartList.get(index);
        }
    }

    public Fart updateFart(int index, Fart fart) {
        if (index >= sGameFartList.size()) {
            return null;
        } else {
            return sGameFartList.set(index, fart);
        }
    }

    public boolean isTablet() {

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return true;
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return true;
        } else {
            return false;
        }
    }

    public static String getVersionName(Context context) {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static int getVersionCode(Context context) {
        PackageInfo pInfo;

        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Fart> getGameFartList() {
        return sGameFartList;
    }
}
