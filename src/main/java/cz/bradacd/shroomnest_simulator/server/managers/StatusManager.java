package cz.bradacd.shroomnest_simulator.server.managers;

import cz.bradacd.shroomnest_simulator.api.entities.Status;
import cz.bradacd.shroomnest_simulator.server.Server;
import cz.bradacd.shroomnest_simulator.utils.MathUtils;

/*
    Singleton class, which manages current temperature and humidity state
 */
public class StatusManager implements SerializableManager {
    private static StatusManager instance = null;
    public static StatusManager getInstance()
    {
        if (instance == null)
            instance = new StatusManager();

        return instance;
    }

    // Initialise on common values
    private static double humidity = 60;
    private static double temperature = 20;

    public static double getHumidity() {
        return humidity;
    }

    public static double getTemperature() {
        return temperature;
    }

    // Possible humidity range 30 % - 96 %
    private static void setHumidity(double newHumidity) {
        if (newHumidity <= 30.0) {
            humidity = 30;
            return;
        }

        if (newHumidity >= 96.0) {
            humidity = 96;
            return;
        }
        humidity = newHumidity;
    }

    // Possible temperature range 12 - 30
    private static void setTemperature(double newTemperature) {
        if (newTemperature <= 12) {
            temperature = 12;
            return;
        }

        if (newTemperature >= 30) {
            temperature = 30;
            return;
        }
        temperature = newTemperature;
    }

    public static void updateStatus() {
        var changeMultiplier = Server.SERVER_PERIOD / 1000;

        // If humidifier on and fan off -> humidity goes up by 0.05 %
        // If humidifier on and fan on -> humidity goes up by 0.03 %
        // If humidifier off and fan off
            // -> coinflip to stay the same
            // -> if coinflip failed currentHumidity % chance to go 0.01 up ... 0.01 down if it fails
        // humidifier off, fan on -> humimdity goes down by 0.1 % if over 80, 0.05 % if over 60, 0.02 % after


        // temperature
        // if fan on - drop by 0.01
        // if fan off stay the same or random up and down by 0.01
    }


    @Override
    public Status getRecord() {
        return new Status(MathUtils.roundToTwoDecimals(humidity), MathUtils.roundToTwoDecimals(temperature));
    }
}
