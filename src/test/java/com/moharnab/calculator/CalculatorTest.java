 package com.moharnab.calculator;

import com.moharnab.calculator.Calculator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Calculator.
 */
public class CalculatorTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public CalculatorTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    TestSuite suite = new TestSuite(CalculatorTest.class.getSimpleName());
    suite.addTest(new CalculatorTest("twoNumbersShouldBeMultiplied"));
    suite.addTest(new CalculatorTest("twoNumbersShouldBeAdded"));
    suite.addTest(new CalculatorTest("twoNumbersShouldBeSubtracted"));
    suite.addTest(new CalculatorTest("expressionShouldBeTakenAsArgument"));
    suite.addTest(new CalculatorTest("letShouldDefineVariable"));
    suite.addTest(new CalculatorTest("letShouldTakeExpressionAsValue"));
    suite.addTest(new CalculatorTest("shouldFailForUnassignedVariables"));
    suite.addTest(new CalculatorTest("letShouldSuuccessfyllyAssignSameValueInDifferentExprs"));
    suite.addTest(new CalculatorTest("shouldFailForIncorrectExpressions"));
    return suite;
  }

  /**
   * Test for adding two numbers
   * 
   * @throws Exception
   */
  public void twoNumbersShouldBeAdded() throws Exception {
    assertEquals(Integer.valueOf(5), new Calculator().evaluate("add(2, 3)"));
  }

  public void twoNumbersShouldBeMultiplied() throws Exception {
    assertEquals(Integer.valueOf(6), new Calculator().evaluate("mult(2, 3)"));
  }

  public void twoNumbersShouldBeSubtracted() throws Exception {
    assertEquals(Integer.valueOf(3), new Calculator().evaluate("sub(8, 5)"));
  }

  public void oneNumberShouldBeDividedByOther() throws Exception {
    assertEquals(Integer.valueOf(5), new Calculator().evaluate("div(10, 2)"));
  }

  public void expressionShouldBeTakenAsArgument() throws Exception {
    assertEquals(Integer.valueOf(6), new Calculator().evaluate("mult(2, add(1, 2))"));
  }

  public void letShouldDefineVariable() throws Exception {
    assertEquals(Integer.valueOf(10), new Calculator().evaluate("let(a, 5, add(a, a))"));
  }

  public void letShouldTakeExpressionAsValue() throws Exception {
    assertEquals(Integer.valueOf(40),
        new Calculator().evaluate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))"));
  }

  public void letShouldSuuccessfyllyAssignSameValueInDifferentExprs() throws Exception {
    assertEquals(Integer.valueOf(55),
        new Calculator().evaluate("let(a, 5, let(b, mult(a, 10), add(b, a)))"));
  }

  public void shouldFailForUnassignedVariables() throws Exception {
    boolean thrown = false;
    try {
      new Calculator().evaluate("add(1, x)");
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
  
  public void shouldFailForIncorrectExpressions() throws Exception {
    boolean thrown = false;
    try {
      new Calculator().evaluate("add(1, x)))");
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}
