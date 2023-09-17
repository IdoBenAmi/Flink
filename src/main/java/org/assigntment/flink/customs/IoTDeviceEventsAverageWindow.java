package org.assigntment.flink.customs;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.assigntment.models.IoTDeviceEvent;

public class IoTDeviceEventsAverageWindow implements WindowFunction<IoTDeviceEvent, Tuple2<String,Float>, String, TimeWindow> {

    @Override
    public void apply(String key, TimeWindow window, Iterable<IoTDeviceEvent> values, Collector<Tuple2<String,Float>> out) {
        float sum = 0F;
        int numberOfEvents=0;
        for (IoTDeviceEvent value : values) {
            sum += value.temperature;
            numberOfEvents++;
        }
        Float avg = sum/numberOfEvents;
        out.collect(new Tuple2<>(key, avg));
    }
}