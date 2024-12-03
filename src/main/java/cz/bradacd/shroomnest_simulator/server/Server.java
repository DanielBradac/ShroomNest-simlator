package cz.bradacd.shroomnest_simulator.server;

import cz.bradacd.shroomnest_simulator.server.managers.StatusManagerSingleton;
import org.springframework.stereotype.Service;

@Service
public class Server {
    // Server run period in millis - how often we simulate state update
    public static final int SERVER_PERIOD = 1000;
    // Errand run period in millis - how often we run big errand (updating managers state)
    private static final int ERRAND_PERIOD = 10000;

    private int errandCounter = 0;

    public void runServer() {
        Thread.startVirtualThread(() -> {
            while (true) {
                try {
                    runTick();
                    Thread.sleep(Server.SERVER_PERIOD);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        updateShroomNestState();
        if (shouldRunErrand()) {
            errandRun();
        }
    }

    private void runTick() {
        updateShroomNestState();
        if (shouldRunErrand()) {
            errandRun();
        }
    }

    private boolean shouldRunErrand() {
        errandCounter += SERVER_PERIOD;
        if (errandCounter >= ERRAND_PERIOD) {
            errandCounter = 0;
            return true;
        }
        return false;
    }

    private void updateShroomNestState() {
        StatusManagerSingleton.updateStatus();
    }

    private void errandRun() {
        System.out.println("Running errand");
    }
}
