package cz.bradacd.shroomnest_simulator.records;

public record VentilationSettings(
        int waitTime,
        int waitPer,
        int runPer,
        String mode,
        int runTime,
        boolean fanOn
) {}



