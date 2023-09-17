package org.assigntment.builders;

import org.assigntment.models.IoTDeviceEvent;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Random;

public class IoTDeviceEventBuilder implements Serializable {
    public String buildDeviceId(int index) {
        return String.format("device-%s", index);
    }

    public IoTDeviceEvent generateDeviceEvent(String deviceId) {
        IoTDeviceEvent newEvent = new IoTDeviceEvent();
        newEvent.deviceId = deviceId.toString();
        newEvent.timestamp = System.currentTimeMillis();
        newEvent.temperature = (long) (Math.random() * 100);
        return newEvent;
    }
}
