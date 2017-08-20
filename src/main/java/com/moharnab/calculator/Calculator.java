package com.moharnab.calculator.exprcalculator;

import java.math.BigInteger;
import java.text.ParseException;


public class Calculator {
  Integer evaluate(String expression) throws Exception{
    Parser parser = new Parser();
    String trimmedExpression = expression.replaceAll("\\s+", "");
    ParseTreeNode expressionNode = parser.parse(trimmedExpression);
    return expressionNode.evaluate();
  }
}
