package com.moharnab.calculator.exprcalculator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Parser {

  static List<ParseTreeNode> scanArguments(final String expression, final int beginning,
      final int end) throws Exception {
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
      throw new ParseException("The expression is not properly formatted", 0);
    return args;
  }

  static ParseTreeNode parse(String expression) throws Exception {
    try {
      int n = Integer.parseInt(expression);
      return new ParseTreeNode(n);
    } catch (final NumberFormatException e) {
      // NaN
    }
    if (expression.matches("[a-zA-Z]+")) {
      return new ParseTreeNode(expression);
    }
    if (expression.startsWith("add")) {
      return parseTwo("add", expression);
    } else if (expression.startsWith("mult")) {
      return parseTwo("mult", expression);
    } else if (expression.startsWith("div")) {
      return parseTwo("div", expression);
    } else if (expression.startsWith("sub")) {
      return parseTwo("sub", expression);
    }else if (expression.startsWith("let")) {
      return parseThree("let", expression);
    }

    throw new ParseException("Invalid operator used - only add, mult, div and let allowed", 0);
  }

  static private ParseTreeNode parseTwo(String op, String expression) throws Exception {
    List<ParseTreeNode> args = scanArguments(expression, op.length(), expression.length());
    if (args.size() != 2)
      throw new ParseException("Invalid number of arguments for " + op + " operator", 0);
    ParseTreeNode first = args.get(0);
    ParseTreeNode second = args.get(1);
    return new ParseTreeNode(op, first, second);
  }

  static private ParseTreeNode parseThree(String op, String expression) throws Exception {
    List<ParseTreeNode> args = scanArguments(expression, op.length(), expression.length());

    if (args.size() != 3)
      throw new ParseException("Invalid number of arguments for " + op + " operator", 0);
    ParseTreeNode first = args.get(0);
    ParseTreeNode second = args.get(1);
    ParseTreeNode third = args.get(2);
    return new ParseTreeNode(op, first.variable, second, third);

  }
}
