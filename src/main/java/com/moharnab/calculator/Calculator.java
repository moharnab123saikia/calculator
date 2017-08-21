package com.moharnab.calculator;

import java.text.ParseException;

public class Calculator {
  
  /**
   * Evaluate the given expression.
   *
   * @param expression The expression to evaluate
   * @return The integer result after evaluating the expression
   * @throws Exception Exception for incorrect expression
   */
  Integer evaluate(String expression) throws Exception{
    String trimmedExpression = expression.replaceAll("\\s+", "");
    ParseTreeNode expressionNode = Parser.parse(trimmedExpression);
    return expressionNode.evaluate();
  }
}
