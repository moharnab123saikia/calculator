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
      }
      if (args.length == 1) { // Default logging level 
        CustomLogger.setup("error");
      }
      Integer result = calc.evaluate(args[0]);
      System.out.println(result);
    } catch (ParseException e) {
      LOGGER.severe("Invalid expression passed: " + args[0]);
      LOGGER.severe(e.getMessage());
      printUsage();
    } catch (IllegalArgumentException e) {
      LOGGER.severe(e.getMessage());
      printUsage();
    } catch (Exception e) {
      LOGGER.severe(e.getMessage());
      printUsage();
    }
  }

  private static void printUsage() {
    String usage = "Usage:\n" + "java com.moharnab.calculator.Main <expression> <log-level(optional)>\n" + "\n"
        + "  An expression is like:\n" + "  <Function>(<Number/Expression><Number/Expression>):\n"
        + "    Numbers: integers between Integer.MIN_VALUE and Integer.MAX_VALUE\n"
        + "    Variables: strings of characters, where each character is one of a-z, A-Z\n"
        + "    Arithmetic functions: add, sub, mult, div, each taking two arbitrary expressions as arguments. In other words, each argument may be any of the expressions on this list.\n"
        + "    Another oertaion is: \"let\" operator for assigning values to variables:\n"
        + "      let(<variable name>, <value expression>, <expression where variable is used>)\n";
    
    System.out.println(usage);
  }
}
