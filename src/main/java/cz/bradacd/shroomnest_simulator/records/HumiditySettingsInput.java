package cz.bradacd.shroomnest_simulator.records;

public record HumiditySettingsInput(
        int waitPer,
        double rangeFrom,
        double rangeTo,
        boolean runWithFan,
        String mode,
        int runPer,
        boolean humidifierOn
) {
}
