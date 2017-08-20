package com.moharnab.calculator.exprcalculator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
  static public void setup(String logLevel) {
    // get the global logger to configure it
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    // set global log level as option passed via console
    switch (logLevel.toLowerCase()) {
      case "info":
        logger.setLevel(Level.INFO);
        break;
      case "error":
        logger.setLevel(Level.SEVERE);
        break;
      case "debug":
        logger.setLevel(Level.CONFIG);
        break;
      default:
        throw new IllegalArgumentException(
            "Invalid log level: " + logLevel + "\n Acceptable levels are - info, error, debug ");
    }
  }
}
