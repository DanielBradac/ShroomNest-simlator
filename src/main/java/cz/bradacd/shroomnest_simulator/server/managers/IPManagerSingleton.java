package cz.bradacd.shroomnest_simulator.server.managers;

import cz.bradacd.shroomnest_simulator.records.IPSettings;

/*
    This is basically a dummy class, because we don't connect to real hardware
 */
public class IPManagerSingleton implements SerializableManager {
    private static final IPManagerSingleton instance = new IPManagerSingleton();
    public static IPManagerSingleton getInstance() {
        return instance;
    }
    private IPManagerSingleton() {}

    private static String humidifierIp = "";
    private static String fanIp = "";

    public synchronized void updateValues(IPSettings newSettings) {
        System.out.println(newSettings);
        humidifierIp = newSettings.humidifierIp();
        fanIp = newSettings.fanIp();
    }

    @Override
    public IPSettings getRecord() {
        return new IPSettings(humidifierIp, fanIp);
    }
}
