package org.assigntment.models;

public class DeviceEvent {
    public String deviceId;
    public long timestamp;

    @Override
    public int hashCode() {
        return deviceId.hashCode();
    }
}
