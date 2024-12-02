package cz.bradacd.shroomnest_simulator.api.entities;

public record Log(
        String header,
        String timestamp,
        String message
) {}
