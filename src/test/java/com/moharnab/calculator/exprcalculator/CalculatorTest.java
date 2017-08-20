package com.moharnab.calculator.exprcalculator;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static junit.framework.Assert.assertEquals;

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
    TestSuite suite = new TestSuite(CalculatorTest.class.getName());
    suite.addTest(new CalculatorTest("twoNumbersShouldBeMultiplied"));
    suite.addTest(new CalculatorTest("twoNumbersShouldBeAdded"));
    suite.addTest(new CalculatorTest("twoNumbersShouldBeSubtracted"));
    suite.addTest(new CalculatorTest("expressionShouldBeTakenAsArgument"));
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
}
