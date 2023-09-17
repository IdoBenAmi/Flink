package org.assigntment.flink.customs;

import org.apache.flink.api.common.functions.FilterFunction;
import org.assigntment.models.AverageIotDeviceEvent;
import static org.assigntment.configurations.Configurations.AVERAGE_DEVIATION_DIFF_ALERT;

public class IotDeviceAverageFilterFunction implements FilterFunction<AverageIotDeviceEvent> {
    @Override
    public boolean filter(AverageIotDeviceEvent event) throws Exception {
        return event.averageTempature + AVERAGE_DEVIATION_DIFF_ALERT < event.temperature ||
                event.averageTempature - AVERAGE_DEVIATION_DIFF_ALERT > event.temperature;
    }
}
