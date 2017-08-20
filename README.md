# Simple Java Expression Evaluator
[![Build Status](https://travis-ci.org/moharnab123saikia/calculator.svg?branch=master)](https://travis-ci.org/moharnab123saikia/calculator)
This is a calculator program in Java that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console.  For example:
# Usage
```
% java calculator.Main add(2, 2)
4
```

Few more examples:

| **Input** | **Output** |
| --- | --- |
| **add(1, 2)** | 3 |
| **add(1, mult(2, 3))** | 7 |
| **mult(add(2, 2), div(9, 3))** | 12 |
| **let(a, 5, add(a, a))** | 10 |
| **let(a, 5, let(b, mult(a, 10), add(b, a)))** | 55 |
| **let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))** | 40 |

An expression is one of the of the following:

- Numbers: integers between Integer.MIN\_VALUE and Integer.MAX\_VALUE
- Variables: strings of characters, where each character is one of a-z, A-Z
- Arithmetic functions: add, sub, mult, div, each taking two _arbitrary expressions_ as arguments.  In other words, each argument may be any of the expressions on this list.
- A &quot;let&quot; operator for assigning values to variables:
**let(&lt;variable name&gt;, &lt;value expression&gt;, &lt;expression where variable is used&gt;)**

As with arithmetic functions, the value expression and the expression where the variable is used may be an arbitrary expression from this list.

## Logging

The application supports 3 levels of verbosity: INFO, ERROR, and DEBUG.  The verbosity to be set via a command-line option passed as the second parameter. If no parameter is passed the logging level is set to INFO. 
```
% java calculator.Main add(2, 2) debug
4
```




