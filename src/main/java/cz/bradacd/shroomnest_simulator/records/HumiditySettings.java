package cz.bradacd.shroomnest_simulator.records;

public record HumiditySettings(
        int waitPer,
        int waitTime,
        double rangeFrom,
        double rangeTo,
        boolean runWithFan,
        String mode,
        int runTime,
        int runPer,
        boolean humidifierOn
) {
}
