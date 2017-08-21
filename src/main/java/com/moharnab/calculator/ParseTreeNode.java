package com.moharnab.calculator;

import java.util.HashMap;

/**
 * The Class ParseTreeNode.
 */
public class ParseTreeNode {

  String operator;
  String variable;
  int value;
  ParseTreeNode leftOperand;
  ParseTreeNode rightOperand;

  /** The variable map to hold values for let expressions */
  HashMap<String, Integer> variableMap = new HashMap<>();

  /**
   * Instantiates a new Parse Tree Node which is a variable.
   *
   * @param variable the variable
   */
  public ParseTreeNode(String variable) {
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
  // Node with expressions add, sub, mult, div
  public ParseTreeNode(String operator, ParseTreeNode leftOperand, ParseTreeNode rightOperand) {
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
  // Node with let expression
  public ParseTreeNode(String operation, String variable, ParseTreeNode value,
      ParseTreeNode expression) {
    this.operator = operation;
    this.leftOperand = value;
    this.rightOperand = expression;
    this.variable = variable;
  }

  /**
   * Evaluate a general arithmetic expression.
   *
   * @return the the integer result
   * @throws Exception exception for invalid expression
   */
  public int evaluate() throws Exception {
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
   * Evaluate.
   *
   * @param variableMap the variable map
   * @return the int result
   * @throws Exception the exception for an invalid let expression
   */
  private int evaluate(HashMap<String, Integer> variableMap) throws Exception {
    if (isConstant())
      return value;
    else if (isVariable())
      try {
        return variableMap.get(variable);
      } catch (Exception e) {
        throw new IllegalArgumentException("Variable used before declaration");
      }
    else if (operator.equals("let")) {
      int variableValue = leftOperand.evaluate(variableMap);
      variableMap.put(this.variable, variableValue);
      return rightOperand.evaluate(variableMap);
    } else if (operator.equals("add")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a + b;
    } else if (operator.equals("mult")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a * b;
    } else if (operator.equals("div")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a / b;
    } else if (operator.equals("sub")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate(variableMap);
      int b = this.rightOperand.evaluate(variableMap);
      return a - b;
    }

    throw new IllegalArgumentException("Invalid operation / variable");
  }
}
