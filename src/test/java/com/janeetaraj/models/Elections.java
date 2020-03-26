package com.janeetaraj.models;

public class Elections {

    private String kind;
    private Election[] elections;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Election[] getElections() {
        return elections;
    }

    public void setElections(Election[] elections) {
        this.elections = elections;
    }

}
