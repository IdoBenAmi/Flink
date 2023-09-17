
package org.assigntment;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.assigntment.flink.customs.IoTDeviceEventsAverageWindow;
import org.assigntment.flink.customs.IoTDeviceEventsDataGenerator;
import org.assigntment.flink.customs.IotDeviceAverageFilterFunction;
import org.assigntment.flink.customs.IotDeviceEventAverageJoinFunction;
import org.assigntment.models.AverageIotDeviceEvent;
import org.assigntment.models.IoTDeviceEvent;
import static java.util.concurrent.TimeUnit.MINUTES;

public class DataStreamJob {

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<IoTDeviceEvent> iotDeviceEvents = env.addSource(new IoTDeviceEventsDataGenerator());
		//iotDeviceEvents.print();

		Time slidingWindowTime = Time.of(1, MINUTES);
		DataStream<Tuple2<String, Float>> averageByDevice = iotDeviceEvents
				.keyBy( (event) -> event.deviceId)
				.window(SlidingProcessingTimeWindows.of(slidingWindowTime, slidingWindowTime))
				.apply(new IoTDeviceEventsAverageWindow());
		//averageByDevice.print();

		DataStream<AverageIotDeviceEvent> filterDevicesOutOfDeviation =  iotDeviceEvents.join(averageByDevice)
						.where(deviceEventKey -> deviceEventKey.deviceId)
						.equalTo(averageByDeviceKey -> averageByDeviceKey.f0)
						.window(TumblingProcessingTimeWindows.of(slidingWindowTime))
						.apply(new IotDeviceEventAverageJoinFunction())
						.filter(new IotDeviceAverageFilterFunction());
		filterDevicesOutOfDeviation.print();

		env.execute("============= Flink Ido Ben-Ami Home Assignment =============");

	}
}
