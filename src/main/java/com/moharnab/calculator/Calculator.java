package com.moharnab.calculator;

import java.text.ParseException;
import java.util.logging.Logger;

public class Calculator {

  /**
   * Evaluate the given expression.
   *
   * @param expression The expression to evaluate
   * @return The integer result after evaluating the expression
   * @throws Exception Exception for incorrect expression
   */
  Integer evaluate(String expression) throws ParseException, IllegalArgumentException{
    // Get global logger instance
    final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String trimmedExpression = expression.replaceAll("\\s+", "");
    ParseTreeNode expressionNode = Parser.parse(trimmedExpression);
    LOGGER.info("Exprssion parsing started: " + trimmedExpression);
    Integer result =  expressionNode.evaluate();
    LOGGER.info("Exprssion parsing completed: " + trimmedExpression);
    return result;
  }
}
