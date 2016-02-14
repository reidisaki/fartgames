package com.kalei.fartgames.models;

import java.util.Date;

/**
 * Created by risaki on 2/14/16.
 */
public class Fart {
    public String id;

    public Authenticity getAuthenticity() {
        return authenticity;
    }

    public void setAuthenticity(final Authenticity authenticity) {
        this.authenticity = authenticity;
    }

    public Authenticity authenticity;
    public Date dateCreated;

    public String getFartURI() {
        return fartURI;
    }

    public void setFartURI(final String fartURI) {
        this.fartURI = fartURI;
    }

    public String fartURI;

    public String getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(final String isCustom) {
        this.isCustom = isCustom;
    }

    public String isCustom;

    public enum Authenticity {
        REAL,
        FAKE;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
