package io.github.explorersodysseygame.game.common.logger;

import java.util.Calendar;

public class Logger {

    // TODO: Allow multiple instances without changing namespace globally

    private String namespace;

    public Logger(String nmsp) {
        namespace = nmsp;
        log(String.format("Created a new Logger under the namespace %s", namespace));
    }
    public void log(String msg) {

        Calendar now = Calendar.getInstance();
        System.out.printf("[%s/%s/%s %s:%s:%s][%s] %s%n",
            parseNumber(now.get(Calendar.DAY_OF_MONTH)),
            parseNumber(now.get(Calendar.MONTH)+1),
            parseNumber(now.get(Calendar.YEAR)),
            parseNumber(now.get(Calendar.HOUR_OF_DAY)),
            parseNumber(now.get(Calendar.MINUTE)),
            parseNumber(now.get(Calendar.SECOND)),
            namespace,
            msg);

    }

    public String parseNumber(int nmb) {
        if (nmb < 10) {
            return String.format("0%s", nmb);
        } else {
            return String.valueOf(nmb);
        }
    }
}
