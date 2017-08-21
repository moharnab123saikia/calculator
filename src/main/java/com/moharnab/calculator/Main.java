package com.moharnab.calculator;

import java.text.ParseException;
import java.util.logging.Logger;

public class Main {
  // Get global logger instance
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    try {
      // Set up logging level
      if (args.length == 2) {
        CustomLogger.setup(args[1]);
        LOGGER.config("Custom log level: " + args[1]);
      }
      if (args.length == 1) { //Default logging level if 
        CustomLogger.setup("info");
        LOGGER.info("Default log level set: INFO");
      }
      Integer result = calc.evaluate(args[0]);
      System.out.println(result);
      //LOGGER.info("Expression result: " + args[0] + " = " + result);
    } catch (ParseException e) {
      LOGGER.severe("Invalid expression passed: " + args[0]);
      LOGGER.severe(e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.severe(e.getMessage());
    } catch (Exception e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
