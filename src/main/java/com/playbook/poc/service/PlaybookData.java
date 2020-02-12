package com.playbook.poc.service;

import com.playbook.poc.utility.PlaybookDataType;

public class PlaybookData {
    String playbookValue;
    PlaybookDataType PlaybookType;
    String playbookDateFormat;

    public PlaybookData(String playbookValue, PlaybookDataType playbookType, String playbookDateFormat) {
        this.playbookValue = playbookValue;
        PlaybookType = playbookType;
        this.playbookDateFormat = playbookDateFormat;
    }

    public String getPlaybookValue() {
        return playbookValue;
    }

    public void setPlaybookValue(String playbookValue) {
        this.playbookValue = playbookValue;
    }

    public PlaybookDataType getPlaybookType() {
        return PlaybookType;
    }

    public void setPlaybookType(PlaybookDataType playbookType) {
        PlaybookType = playbookType;
    }

    public String getPlaybookDateFormat() {
        return playbookDateFormat;
    }

    public void setPlaybookDateFormat(String playbookDateFormat) {
        this.playbookDateFormat = playbookDateFormat;
    }

    @Override
    public String toString() {
        return "PlaybookData{" +
                "playbookValue='" + playbookValue + '\'' +
                ", PlaybookType=" + PlaybookType +
                ", playbookDateFormat='" + playbookDateFormat + '\'' +
                '}';
    }
}
