package com.kalei.fartgames.enums;

/**
 * Created by risaki on 2/14/16.
 */
public enum Authenticity {
    REAL("real"),
    FAKE("fake"),
    NULL("null");

    private String mValue;

    Authenticity(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public static Authenticity fromValue(String value) {
        for (Authenticity e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return NULL;
    }
}