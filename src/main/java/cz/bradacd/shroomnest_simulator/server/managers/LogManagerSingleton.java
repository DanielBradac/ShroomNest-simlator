package cz.bradacd.shroomnest_simulator.server.managers;

import cz.bradacd.shroomnest_simulator.records.Log;
import cz.bradacd.shroomnest_simulator.records.Logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogManagerSingleton implements SerializableManager {
    private static final LogManagerSingleton instance = new LogManagerSingleton();
    public static LogManagerSingleton getInstance() {
        return instance;
    }
    private LogManagerSingleton() {}

    private List<Log> info = new ArrayList<>();
    private List<Log> warning = new ArrayList<>();
    private List<Log> error = new ArrayList<>();

    public enum Levels {
        INFO,
        WARNING,
        ERROR
    }

    public void logMessage(Levels level, String header, String message) {
        if (level == null) {
            throw new IllegalArgumentException("Level is mandatory");
        }
        String timeStamp = getCurrentTimeStamp();
        Log newLogRecord = new Log(header == null ? "":header, timeStamp, message == null ? "" : message);

        switch (level) {
            case INFO -> info.add(newLogRecord);
            case WARNING -> warning.add(newLogRecord);
            case ERROR -> error.add(newLogRecord);
        }
    }

    private String getCurrentTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }


    @Override
    public Logs getRecord() {
        return new Logs(info, warning, error);
    }

    public synchronized Record purgeLogs() {
        info.clear();
        warning.clear();
        error.clear();
        return new Logs(info, warning, error);
    }
}
