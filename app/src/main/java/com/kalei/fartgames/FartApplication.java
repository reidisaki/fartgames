package com.kalei.fartgames;

import com.kalei.fartgames.models.Fart;
import com.kalei.fartgames.utils.FartGenerator;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by risaki on 2/14/16.
 */
public class FartApplication extends Application {
    private static FartApplication mInstance;
    private static ArrayList<Fart> sFartList;
    private static ArrayList<Fart> sGameFartList;
    private boolean mIsSplashInitialized = false;
    private static int TOTAL_QUESTIONS = 5;
    private static int TOTAL_FARTS = 5;

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

    public ArrayList<Fart> getGameFartList() {
        return sGameFartList;
    }
}
