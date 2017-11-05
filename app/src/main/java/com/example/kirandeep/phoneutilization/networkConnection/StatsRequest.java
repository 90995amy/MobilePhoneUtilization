package com.example.kirandeep.phoneutilization.networkConnection;

import com.example.kirandeep.phoneutilization.localStorage.StatsModel;

/**
 * Created by abc on 04-11-2017.
 */

public class StatsRequest {

    private String deviceId;
    private String appName;
    private String lastTimeUsed;
    private String firstTimestamp;
    private String lastTimestamp;
    private String totalTimeInForeground;

    public StatsRequest(StatsModel statsModel) {
        this.deviceId = statsModel.getDeviceId();
        this.appName = statsModel.getAppName();
        this.lastTimestamp = statsModel.getLastTimestamp();
        this.firstTimestamp = statsModel.getFirstTimestamp();
        this.lastTimeUsed = statsModel.getLastTimeUsed();
        this.totalTimeInForeground = statsModel.getTotalTimeInForeground();
    }
}
