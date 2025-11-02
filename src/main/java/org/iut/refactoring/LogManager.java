package org.iut.refactoring;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
    private List<String> logs = new ArrayList<>();
    public void ajouterLog(String message) {
        logs.add(message);
    }

    public void afficherLogs() {
        System.out.println("=== LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public List<String> getLogs() {
        return logs;
    }
}
