package com.kalei.fartgames.models;

import com.kalei.fartgames.enums.Authenticity;

import java.util.Date;

/**
 * Created by risaki on 2/14/16.
 */
public class Fart {
    public String id;

    public Authenticity authenticity;
    public Date dateCreated;
    public String fartURI;
    public boolean isCustom;
    public int rawId;

    public boolean isMarkedCorrect() {
        return markedCorrect;
    }

    public void setMarkedCorrect(final boolean markedCorrect) {
        this.markedCorrect = markedCorrect;
    }

    public boolean markedCorrect;

    public int getRawId() {
        return rawId;
    }

    public void setRawId(final int rawId) {
        this.rawId = rawId;
    }

    public Authenticity getAuthenticity() {
        return authenticity;
    }

    public void setAuthenticity(final Authenticity authenticity) {
        this.authenticity = authenticity;
    }

    public String getFartURI() {
        return fartURI;
    }

    public void setFartURI(final String fartURI) {
        this.fartURI = fartURI;
    }

    public boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(final boolean isCustom) {
        this.isCustom = isCustom;
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

    public Fart() {

    }

    public Fart(boolean isCustom, Authenticity authenticity, int rawId) {
        this.isCustom = isCustom;
        this.authenticity = authenticity;
        this.rawId = rawId;
    }
}
