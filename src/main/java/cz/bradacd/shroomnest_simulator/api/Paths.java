package cz.bradacd.shroomnest_simulator.api;

public final class Paths {
    // GET
    public static final String GET_STATUS = "/status";
    public static final String GET_HUMIDITY_SETTINGS = "/getHumiditySettings";
    public static final String GET_VENTILATION_SETTINGS = "/getVentilationSettings";
    public static final String GET_LOGS = "/getLogs";

    // POST
    public static final String PURGE_LOGS = "/purgeLogs";
    public static final String UPDATE_HUMIDITY_SETTINGS = "/updateHumiditySettings";
    public static final String UPDATE_VENTILATION_SETTINGS = "/updateVentilationSettings";
    public static final String UPDATE_IP_SETTINGS = "/updateIPSettings";
}
