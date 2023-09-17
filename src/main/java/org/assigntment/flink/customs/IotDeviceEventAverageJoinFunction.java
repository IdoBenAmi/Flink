package org.assigntment.flink.customs;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.assigntment.models.AverageIotDeviceEvent;
import org.assigntment.models.IoTDeviceEvent;

public class IotDeviceEventAverageJoinFunction implements JoinFunction<IoTDeviceEvent, Tuple2<String,Float>, AverageIotDeviceEvent> {
    @Override
    public AverageIotDeviceEvent join(IoTDeviceEvent first, Tuple2<String,Float> second) {
        AverageIotDeviceEvent deviceEventWithAverage = new AverageIotDeviceEvent();
        deviceEventWithAverage.deviceId = first.deviceId;
        deviceEventWithAverage.temperature = first.temperature;
        deviceEventWithAverage.timestamp = first.timestamp;
        deviceEventWithAverage.averageTempature = second.f1;

        return deviceEventWithAverage;
    }
}
