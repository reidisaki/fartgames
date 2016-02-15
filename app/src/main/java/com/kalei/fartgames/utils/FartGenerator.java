package com.kalei.fartgames.utils;

import com.kalei.fartgames.R;
import com.kalei.fartgames.enums.Authenticity;
import com.kalei.fartgames.models.Fart;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by risaki on 2/14/16.
 */
public class FartGenerator {
    public static Fart randomFart(int totalFarts, Context context) {
        Random random = new Random();
        int randomNumber = random.nextInt();
        Authenticity authenticity = randomNumber % 2 == 0 ? Authenticity.FAKE : Authenticity.FAKE;

        return new Fart(false, authenticity, context.getResources()
                .getIdentifier(createResourceURI(authenticity, randomNumber), "raw", context.getPackageName()));
    }

    private static String createResourceURI(Authenticity authenticity, int randomNumber) {
        return authenticity.toString().toLowerCase() + "_" + randomNumber;
    }

    public static int numberOfSounds() {
        Field[] fields = R.raw.class.getFields();
        return fields.length;
    }

    public static void loadFarts(List<Fart> fartList, Context context) {
        Field[] fields = R.raw.class.getFields();
        for (int count = 0; count < fields.length; count++) {
            Fart fart = new Fart();

            if (fields[count].getName().contains(context.getString(R.string.real).toLowerCase())) {
                fart.setAuthenticity(Authenticity.REAL);
            } else {
                fart.setAuthenticity(Authenticity.FAKE);
            }
            Log.i("Reid", "fart is real : " + fart.getAuthenticity().toString() + " name: " + fields[count].getName());
            fart.setId(String.valueOf(count));
            fart.setRawId(context.getResources()
                    .getIdentifier(fields[count].getName(), "raw", context.getPackageName()));

            fart.setIsCustom(false);
            fartList.add(fart);
        }
    }

    public static ArrayList<Fart> shuffleFarts(ArrayList<Fart> ar, int totalQuestions) {
        ArrayList<Fart> fartlist = new ArrayList<>();
        Collections.shuffle(ar, new Random(System.nanoTime()));

        if (totalQuestions > ar.size()) {
            totalQuestions = ar.size();
        }

        for (int i = 0; i < totalQuestions; i++) {
            fartlist.add(ar.get(i));
            Log.i("Reid", "raw id: " + ar.get(i).getRawId());
        }

        return fartlist;
    }
    //load up fart files into an array, and randomize 10 of them.
}
