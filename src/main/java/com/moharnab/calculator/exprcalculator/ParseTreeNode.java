package com.moharnab.calculator.exprcalculator;

import java.util.HashMap;

public class ParseTreeNode {
  String operator;
  String variable;
  int value;
  ParseTreeNode leftOperand;
  ParseTreeNode rightOperand;

  // Node with integer value
  public ParseTreeNode(int number) {
    this.value = number;
    this.variable = null;
    this.operator = null;
  }

  // Node with expressions add, sub, mult, div
  public ParseTreeNode(String operator, ParseTreeNode leftOperand, ParseTreeNode rightOperand) {
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.variable = null;
  }

  public int evaluate() throws Exception {
    if (isConstant())
      return value;
    else if (operator.equals("add")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate();
      int b = this.rightOperand.evaluate();
      return a + b;
    } else if (operator.equals("mult")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate();
      int b = this.rightOperand.evaluate();
      return a * b;
    } else if (operator.equals("div")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate();
      int b = this.rightOperand.evaluate();
      return a / b;
    } else if (operator.equals("sub")) {
      if (variable != null) {
        throw new Exception("Invalid operator exception");
      }
      int a = this.leftOperand.evaluate();
      int b = this.rightOperand.evaluate();
      return a - b;
    }
    throw new IllegalArgumentException("Invalid operation / variable");
  }

  private boolean isConstant() {
    if (operator == null && variable == null)
      return true;
    else
      return false;
  }
}
