package com.framework.decorators;
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
public class LoggingDecorator {
    private static final Logger logger = LogManager.getLogger(LoggingDecorator.class);
    public static void info(String msg){ logger.info(msg);
    }
}
