package com.example.kirandeep.phoneutilization.localStorage;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kirandeep on 17-10-2017.
 */

public class StatsModel extends RealmObject{

    private String deviceId;
    @PrimaryKey
    private String appName;
    private String lastTimeUsed;
    private String firstTimestamp;
    private String lastTimestamp;
    private String totalTimeInForeground;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(String lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public String getFirstTimestamp() {
        return firstTimestamp;
    }

    public void setFirstTimestamp(String firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    public String getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(String lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public String getTotalTimeInForeground() {
        return totalTimeInForeground;
    }

    public void setTotalTimeInForeground(String totalTimeInForeground) {
        this.totalTimeInForeground = totalTimeInForeground;
    }
}
