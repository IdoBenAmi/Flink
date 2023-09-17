package org.assigntment.models;

import java.io.Serializable;

public class IoTDeviceEvent extends DeviceEvent implements Serializable {
    public float temperature;

    @Override
    public String toString() {
        return String.format("Device %s, measurement %s C, time %s", deviceId, temperature, timestamp );
    }
}
