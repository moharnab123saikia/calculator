package com.moharnab.calculator;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
  
  /**
   * Sets the up logging level.
   *
   * @param logLevel the log level to configure.
   * @throws IllegalArgumentException Throws error if invalid log level is passed.
   * 			Supported levels are: info, error, debug
   */
  static public void setup(String logLevel) throws IllegalArgumentException{
    // get the global logger to configure it
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    ConsoleHandler handler = new ConsoleHandler();
    
    // set global log level as option passed via console
    switch (logLevel.toLowerCase()) {
      case "info":
        logger.setLevel(Level.INFO);
        handler.setLevel(Level.INFO);
        logger.addHandler(handler);
        break;
      case "error":
        logger.setLevel(Level.SEVERE);
        handler.setLevel(Level.SEVERE);
        logger.addHandler(handler);
        break;
      case "debug":
        logger.setLevel(Level.CONFIG);
        handler.setLevel(Level.CONFIG);
        logger.addHandler(handler);
        break;
      default:
        throw new IllegalArgumentException(
            "Invalid log level: " + logLevel + "\n Acceptable levels are - info, error, debug ");
    }
  }
}
