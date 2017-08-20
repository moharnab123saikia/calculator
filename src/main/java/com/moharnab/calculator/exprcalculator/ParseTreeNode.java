package com.moharnab.calculator.exprcalculator;

import java.util.HashMap;

public class ParseTreeNode {
  String operator;
  String variable;
  int value;
  ParseTreeNode leftOperand;
  ParseTreeNode rightOperand;
  HashMap<String, Integer> variableMap = new HashMap<>();

  //Node with variable name
  public ParseTreeNode(String variable){
    this.variable = variable;
    this.operator = null;
    this.leftOperand = null;
    this.rightOperand = null;
  }

  //Node with integer value
  public ParseTreeNode(int number){
    this.value = number;
    this.variable = null;
    this.operator = null;
  }
  
  //Node with expressions add, sub, mult, div
  public ParseTreeNode(String operator, ParseTreeNode leftOperand, ParseTreeNode rightOperand){
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.variable = null;
  }

  //Node with let expression
  public ParseTreeNode(String operation, String variable, ParseTreeNode value, ParseTreeNode expression){
    this.operator = operation;
    this.leftOperand = value;
    this.rightOperand = expression;
    this.variable = variable;
  }

  public int evaluate() throws Exception {
    return this.evaluate(variableMap);
  }

  private boolean isVariable() {
    if (operator == null && variable != null)
      return true;
    else
      return false;
  }

  private boolean isConstant() {
    if (operator == null && variable == null)
      return true;
    else
      return false;
  }

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
    }else if (operator.equals("sub")) {
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
