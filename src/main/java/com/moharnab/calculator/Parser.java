package com.moharnab.calculator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Parser {

  /**
   * Gets the expression arguments.
   *
   * @param expression the expression
   * @param beginning the start index of the expression arguments in the string
   * @param end the end index of the expression arguments in the string
   * @return the List<ParseTreeNode> of expression arguments
   * @throws ParseException the parse exception for invalid format
   */
  static List<ParseTreeNode> getExpressionArguments(String expression, int beginning, int end)
      throws ParseException {
    final List<ParseTreeNode> args = new ArrayList<>();
    int stack = 0;
    int start = beginning;
    for (int i = start; i < end; i++) {
      final char nextCharacter = expression.charAt(i);
      if (nextCharacter == '(') {
        if (stack == 0) {
          start = i + 1;
        }
        stack++;
      } else if (nextCharacter == ')') {
        stack--;
        if (stack == 0) {
          args.add(parse(expression.substring(start, i)));
        }
      } else if (nextCharacter == ',' && stack == 1) {
        args.add(parse(expression.substring(start, i)));
        start = i + 1;
      }
    }
    if (stack != 0)
      throw new ParseException("Invalid expression format.", 0);
    return args;
  }

  /**
   * Parses the expression.
   *
   * @param expression the expression string to parse
   * @return the ParseTreeNode
   * @throws ParseException the parse exception for invalid expression 
   * */
  static ParseTreeNode parse(String expression) throws ParseException {
    try {
      int n = Integer.parseInt(expression);
      return new ParseTreeNode(n);
    } catch (final NumberFormatException e) {
      // Not a number
    }
    if (expression.matches("[a-zA-Z]+")) {
      return new ParseTreeNode(expression);
    }
    if (expression.startsWith("add")) {
      return parseGeneralExpression("add", expression);
    } else if (expression.startsWith("mult")) {
      return parseGeneralExpression("mult", expression);
    } else if (expression.startsWith("div")) {
      return parseGeneralExpression("div", expression);
    } else if (expression.startsWith("sub")) {
      return parseGeneralExpression("sub", expression);
    } else if (expression.startsWith("let")) {
      return parseLetExpression("let", expression);
    }

    throw new ParseException("Invalid operator used - only add, sub, mult, div and let allowed.", 0);
  }

  /**
   * Parses the general expression: add, sub, mult, div.
   *
   * @param operator the operator which is one of add, sub, mult, div
   * @param expression the expression string
   * @return the ParseTreeNode for the expression
   * @throws ParseException the parse exception for invalid expression 
   */
  static private ParseTreeNode parseGeneralExpression(String operator, String expression)
      throws ParseException {
    List<ParseTreeNode> args =
        getExpressionArguments(expression, operator.length(), expression.length());
    if (args.size() != 2)
      throw new ParseException("Invalid number of arguments for " + operator + " operator.", 0);
    ParseTreeNode first = args.get(0);
    ParseTreeNode second = args.get(1);
    return new ParseTreeNode(operator, first, second);
  }

  /**
   * Parses the let expression.
   *
   * @param operator the operator which is 'let'
   * @param expression the expression string
   * @return the ParseTreeNode for the expression
   * @throws ParseException the parse exception for invalid expression 
   */
  static private ParseTreeNode parseLetExpression(String operator, String expression)
      throws ParseException {
    List<ParseTreeNode> arguments =
        getExpressionArguments(expression, operator.length(), expression.length());

    if (arguments.size() != 3)
      throw new ParseException("Invalid number of arguments for " + operator + " operator.", 0);
    ParseTreeNode first = arguments.get(0);
    ParseTreeNode second = arguments.get(1);
    ParseTreeNode third = arguments.get(2);
    return new ParseTreeNode(operator, first.variable, second, third);

  }
}
