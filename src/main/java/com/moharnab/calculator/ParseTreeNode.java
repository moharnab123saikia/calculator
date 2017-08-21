package com.moharnab.calculator;

import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class ParseTreeNode.
 */
public class ParseTreeNode {

  String operator;
  String variable;
  int value;
  ParseTreeNode leftOperand;
  ParseTreeNode rightOperand;
  /**Get global logger instance**/
  final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  /** The variable map to hold values for let expressions */
  HashMap<String, Integer> variableMap = new HashMap<>();

  /**
   * Instantiates a new Parse Tree Node which is a variable.
   *
   * @param variable the variable
   */
  public ParseTreeNode(String variable) {
    LOGGER.log(Level.CONFIG, "Creating variable node.", operator);
    this.variable = variable;
    this.operator = null;
    this.leftOperand = null;
    this.rightOperand = null;
  }

  /**
   * Instantiates a new Parse Tree Node which is a Integer value.
   *
   * @param number Integer value
   */
  public ParseTreeNode(int number) {
    LOGGER.log(Level.CONFIG, "Creating value node.", operator);
    this.value = number;
    this.variable = null;
    this.operator = null;
  }

  /**
   * Instantiates a new Parse Tree Node which is a arithmetic expression value.
   *
   * @param operator the operator
   * @param leftOperand the left operand
   * @param rightOperand the right operand
   */
  public ParseTreeNode(String operator, ParseTreeNode leftOperand, ParseTreeNode rightOperand) {
    LOGGER.log(Level.CONFIG, "Creating general expression node.", operator);
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.variable = null;
  }

  /**
   * Instantiates a new Parse Tree Node which is a let expression.
   *
   * @param operation let operation
   * @param variable the variable
   * @param value the value for the variable
   * @param expression the expression to use the variable
   */
  public ParseTreeNode(String operation, String variable, ParseTreeNode value,
      ParseTreeNode expression) {
    LOGGER.log(Level.CONFIG, "Creating let expression node.", operator);
    this.operator = operation;
    this.leftOperand = value;
    this.rightOperand = expression;
    this.variable = variable;
  }

  /**
   * Evaluate a general arithmetic expression.
   *
   * @return the the integer result
   * @throws ParseException exception for invalid expression
   */
  public int evaluate() throws ParseException {
    return this.evaluate(variableMap);
  }

  /**
   * Checks if the current ParseTreeNode is variable.
   *
   * @return true, if is variable
   */
  private boolean isVariable() {
    if (operator == null && variable != null)
      return true;
    else
      return false;
  }

  /**
   * Checks if the current ParseTreeNode is Constant.
   *
   * @return true, if is constant
   */
  private boolean isConstant() {
    if (operator == null && variable == null)
      return true;
    else
      return false;
  }

  /**
   * Evaluate an expression.
   *
   * @param variableMap the variable map
   * @return the int result
   * @throws Exception the exception for an invalid let expression
   */
  private int evaluate(HashMap<String, Integer> variableMap) throws ParseException {
    if (isConstant())
      return value;
    else if (isVariable())
      try {
        return variableMap.get(variable);
      } catch (Exception e) {
        throw new IllegalArgumentException("Variable used before declaration");
      }
    else if (operator.equals("let")) {
      LOGGER.log(Level.CONFIG, "Evaluating let expression", variable);
      int variableValue = leftOperand.evaluate(variableMap);
      variableMap.put(this.variable, variableValue);
      return rightOperand.evaluate(variableMap);
    } else if (operator.equals("add")) {
      if (variable != null) {
        throw new ParseException("Invalid operation.", 0);
      }
      LOGGER.log(Level.CONFIG, "Evaluating add expression", operator);
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a + b;
    } else if (operator.equals("mult")) {
      if (variable != null) {
        throw new ParseException("Invalid operation.", 0);
      }
      LOGGER.log(Level.CONFIG, "Evaluating mult expression", operator);
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a * b;
    } else if (operator.equals("div")) {
      if (variable != null) {
        throw new ParseException("Invalid operation.", 0);
      }
      LOGGER.log(Level.CONFIG, "Evaluating div expression", operator);
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a / b;
    } else if (operator.equals("sub")) {
      if (variable != null) {
        throw new ParseException("Invalid operation.", 0);
      }
      LOGGER.log(Level.CONFIG, "Evaluating sub expression", operator);
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a - b;
    }

    throw new ParseException("Invalid operation / variable", 0);
  }
}
