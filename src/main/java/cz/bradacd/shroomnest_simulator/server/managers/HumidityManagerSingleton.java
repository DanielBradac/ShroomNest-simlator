package cz.bradacd.shroomnest_simulator.server.managers;


import cz.bradacd.shroomnest_simulator.exceptions.ValidationException;
import cz.bradacd.shroomnest_simulator.records.HumiditySettings;
import cz.bradacd.shroomnest_simulator.records.HumiditySettingsInput;

public class HumidityManagerSingleton implements SerializableManager {
    private static final HumidityManagerSingleton instance = new HumidityManagerSingleton();

    public static HumidityManagerSingleton getInstance() {
        return instance;
    }

    private HumidityManagerSingleton() {
    }

    private enum HumidifierMode {
        Manual,
        Periodic,
        Automatic
    }

    private int waitPer = 0;
    private int runPer = 0;
    private volatile int waitTime = 0;
    private volatile int runTime = 0;
    private double rangeFrom = 0;
    private double rangeTo = 100;
    private boolean runWithFan = false;
    private HumidifierMode mode = HumidifierMode.Manual;
    private volatile boolean humidifierOn = false;

    final Object updateLock = new Object();

    public void updateValues(HumiditySettingsInput newSettings) throws ValidationException {
        validateInput(newSettings);
        synchronized (updateLock) {
            waitPer = newSettings.waitPer();
            runPer = newSettings.runPer();
            rangeFrom = newSettings.rangeFrom();
            rangeTo = newSettings.rangeTo();
            runWithFan = newSettings.runWithFan();
            mode = stringToMode(newSettings.mode());
            humidifierOn = newSettings.humidifierOn();

            if (mode == HumidifierMode.Manual) {
                LogManagerSingleton.getInstance().logMessage(
                        LogManagerSingleton.Levels.INFO,
                        "Humidifier - MANUAL",
                        "Manual toggle " + (humidifierOn ? "ON" : "OFF")
                );
                toggleHumidifier(humidifierOn);
            }

            if (mode == HumidifierMode.Periodic) {
                resetPeriodicTimers();
            }
        }
    }

    private void validateInput(HumiditySettingsInput newSettings) throws ValidationException {
        if (newSettings.rangeFrom() < 0 || newSettings.rangeFrom() > 100) {
            throw new ValidationException("rangeFrom must be in interval <0;100>");
        }
        if (newSettings.rangeTo() < 0 || newSettings.rangeTo() > 100) {
            throw new ValidationException("rangeTo must be in interval <0;100>");
        }
        if (newSettings.rangeFrom() >= newSettings.rangeTo()) {
            throw new ValidationException("rangeFrom must be lower than rangeTo");
        }
        if (newSettings.waitPer() < 0) {
            throw new ValidationException("waitPer must be a positive number");
        }
        if (newSettings.runPer() < 0) {
            throw new ValidationException("runPer must be a positive number");
        }

        var mode = newSettings.mode();
        if (!mode.equals("auto") && !mode.equals("manual") && !mode.equals("period")) {
            throw new ValidationException("mode must be 'manual', 'auto' or 'period'");
        }
    }

    private HumidifierMode stringToMode(String modeStr) {
        return switch (modeStr) {
            case ("auto") -> HumidifierMode.Automatic;
            case ("manual") -> HumidifierMode.Manual;
            case ("period") -> HumidifierMode.Periodic;
            default -> throw new IllegalArgumentException("Unexpected value: " + modeStr);
        };
    }

    private String modeToString(HumidifierMode mode) {
        return switch (mode) {
            case HumidifierMode.Automatic -> "auto";
            case HumidifierMode.Manual -> "manual";
            case HumidifierMode.Periodic -> "period";
        };
    }

    public synchronized void toggleHumidifier(boolean onOff) {
        humidifierOn = onOff;

        LogManagerSingleton.getInstance().logMessage(
                LogManagerSingleton.Levels.INFO,
                "Humidifier TOGGLE DONE",
                "Humidifier switched " + (onOff ? "ON" : "OFF")
        );
    }

    private void resetPeriodicTimers() {
        waitTime = 0;
        runTime = 0;
    }

    @Override
    public HumiditySettings getRecord() {
        return new HumiditySettings(
                waitPer,
                waitTime,
                rangeFrom,
                rangeTo,
                runWithFan,
                modeToString(mode),
                runTime,
                runPer,
                humidifierOn
        );
    }
}
