package de.comdirect.collabothon2016.collabothon2016.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Nutzer {

    public int nutzerId;
    public String name;
    public int globalScores;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
