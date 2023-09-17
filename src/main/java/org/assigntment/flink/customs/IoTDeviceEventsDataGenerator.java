package org.assigntment.flink.customs;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.assigntment.builders.IoTDeviceEventBuilder;
import org.assigntment.models.IoTDeviceEvent;
import java.io.Serializable;
import java.util.*;
import static org.assigntment.configurations.Configurations.*;

public class IoTDeviceEventsDataGenerator implements SourceFunction<IoTDeviceEvent>, Serializable {

    private List<String> deviceIds;
    private List<IoTDeviceEvent> deviceEvents;
    private IoTDeviceEventBuilder builder;
    private boolean keepSendingEvents;


    public IoTDeviceEventsDataGenerator(){
        this.deviceIds = new ArrayList<>();
        this.deviceEvents = new ArrayList<IoTDeviceEvent>();
        this.builder = new IoTDeviceEventBuilder();
        this.initDeviceMap();
    }

    @Override
    public void run(SourceContext<IoTDeviceEvent> sourceContext) throws Exception {
        this.activateEvents();

        while(keepSendingEvents) {
            this.deviceIds.forEach( (deviceId) -> {
                this.generateIoTDeviceEvents(
                        deviceId,
                        (ArrayList<IoTDeviceEvent>) this.deviceEvents);
            });

            this.deviceEvents.iterator().forEachRemaining(
                    remainEvent -> sourceContext.collectWithTimestamp(remainEvent, remainEvent.timestamp));
            Thread.sleep(THREAD_SLEEP);
            this.deviceEvents.clear();

        }
    }

    @Override
    public void cancel() {

    }

    public void deactivateEvents(){
        this.keepSendingEvents = false;
    }

    public void activateEvents(){
        this.keepSendingEvents = true;
    }

    private void initDeviceMap() {
        for (int deviceIdx=0; deviceIdx < NUMBER_OF_DEVICES; deviceIdx++ ) {
            this.deviceIds.add(this.builder.buildDeviceId(deviceIdx));
        }
    }

    private void generateIoTDeviceEvents(String deviceId,
                                         ArrayList<IoTDeviceEvent> deviceEvents) {
        for (int eventIdx=0; eventIdx<NUMBER_OF_EVENTS_PER_DEVICE; eventIdx++) {
            deviceEvents.add(this.builder.generateDeviceEvent(deviceId));
        }
    }
}
