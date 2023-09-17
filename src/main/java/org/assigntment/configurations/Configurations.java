package org.assigntment.configurations;

public class Configurations {
    public static final int NUMBER_OF_DEVICES = System.getenv("NUMBER_OF_DEVICES") == null ? 2000 : Integer.parseInt(System.getenv("NUMBER_OF_DEVICES"));
    public static final int NUMBER_OF_EVENTS_PER_DEVICE = System.getenv("NUMBER_OF_EVENTS_PER_DEVICE") == null ? 25 : Integer.parseInt(System.getenv("NUMBER_OF_EVENTS_PER_DEVICE"));
    public static final int THREAD_SLEEP = System.getenv("THREAD_SLEEP") == null ? 15000 : Integer.parseInt(System.getenv("THREAD_SLEEP"));
    public static final int AVERAGE_DEVIATION_DIFF_ALERT = System.getenv("AVERAGE_DEVIATION_DIFF_ALERT") == null ? 3 : Integer.parseInt(System.getenv("AVERAGE_DEVIATION_DIFF_ALERT"));
}
