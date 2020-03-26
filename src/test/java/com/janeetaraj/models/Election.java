package com.janeetaraj.models;

public class Election {

    private String id;
    private String name;
    private String electionDay;
    private String ocdDivisionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElectionDay() {
        return electionDay;
    }

    public void setElectionDay(String electionDay) {
        this.electionDay = electionDay;
    }

    public String getOcdDivisionId() {
        return ocdDivisionId;
    }

    public void setOcdDivisionId(String ocdDivisionId) {
        this.ocdDivisionId = ocdDivisionId;
    }

}
