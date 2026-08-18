package com.soumyadip.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorEngine {

    /*
     * ==========================================
     * MAIN CALCULATION METHOD
     * ==========================================
     */

    public double calculate(String expression) {

        if (expression == null ||
                expression.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Expression cannot be empty"
            );
        }

        /*
         * Convert display symbols into
         * mathematical operators.
         */

        expression = expression
                .replace("×", "*")
                .replace("÷", "/")
                .replace("−", "-");

        /*
         * Step 1:
         * Convert expression into tokens.
         */

        List<String> tokens =
                tokenize(expression);

        /*
         * Step 2:
         * Convert infix expression into
         * postfix expression.
         */

        List<String> postfix =
                convertToPostfix(tokens);

        /*
         * Step 3:
         * Evaluate postfix expression.
         */

        return evaluatePostfix(postfix);
    }


    /*
     * ==========================================
     * TOKENIZATION
     * ==========================================
     */

    private List<String> tokenize(String expression) {

        List<String> tokens =
                new ArrayList<String>();

        int i = 0;

        while (i < expression.length()) {

            char ch =
                    expression.charAt(i);

            /*
             * Ignore spaces.
             */

            if (Character.isWhitespace(ch)) {

                i++;
                continue;
            }

            /*
             * Read numbers.
             *
             * Example:
             *
             * 123.45
             */

            if (Character.isDigit(ch)
                    || ch == '.') {

                StringBuilder number =
                        new StringBuilder();

                while (i < expression.length()) {

                    char current =
                            expression.charAt(i);

                    if (Character.isDigit(current)
                            || current == '.') {

                        number.append(current);

                        i++;

                    } else {

                        break;
                    }
                }

                tokens.add(
                        number.toString()
                );

                continue;
            }

            /*
             * Operators.
             */

            if ("+-*/%^()".indexOf(ch) >= 0) {

                /*
                 * Handle negative numbers.
                 *
                 * Example:
                 *
                 * -5
                 * 2*-3
                 */

                if (ch == '-') {

                    if (tokens.isEmpty()
                            || tokens.get(
                            tokens.size() - 1
                    ).equals("(")
                            || isOperator(
                            tokens.get(
                                    tokens.size() - 1
                            ))) {

                        tokens.add("u-");

                    } else {

                        tokens.add("-");
                    }

                } else {

                    tokens.add(
                            String.valueOf(ch)
                    );
                }

                i++;

                continue;
            }

            throw new IllegalArgumentException(
                    "Invalid character: " + ch
            );
        }

        return tokens;
    }


    /*
     * ==========================================
     * INFIX → POSTFIX
     * ==========================================
     */

    private List<String> convertToPostfix(
            List<String> tokens) {

        List<String> output =
                new ArrayList<String>();

        Stack<String> operators =
                new Stack<String>();

        for (String token : tokens) {

            /*
             * If token is a number,
             * directly send it to output.
             */

            if (isNumber(token)) {

                output.add(token);
            }

            /*
             * Opening bracket.
             */

            else if (token.equals("(")) {

                operators.push(token);
            }

            /*
             * Closing bracket.
             */

            else if (token.equals(")")) {

                while (!operators.isEmpty()
                        && !operators.peek()
                        .equals("(")) {

                    output.add(
                            operators.pop()
                    );
                }

                if (operators.isEmpty()) {

                    throw new IllegalArgumentException(
                            "Mismatched parentheses"
                    );
                }

                /*
                 * Remove '('
                 */

                operators.pop();
            }

            /*
             * Operator.
             */

            else if (isOperator(token)) {

                while (!operators.isEmpty()
                        && !operators.peek()
                        .equals("(")
                        && precedence(
                        operators.peek()
                ) >= precedence(token)) {

                    output.add(
                            operators.pop()
                    );
                }

                operators.push(token);
            }

            else {

                throw new IllegalArgumentException(
                        "Invalid token: " + token
                );
            }
        }

        /*
         * Empty remaining operators.
         */

        while (!operators.isEmpty()) {

            if (operators.peek()
                    .equals("(")) {

                throw new IllegalArgumentException(
                        "Mismatched parentheses"
                );
            }

            output.add(
                    operators.pop()
            );
        }

        return output;
    }


    /*
     * ==========================================
     * POSTFIX EVALUATION
     * ==========================================
     */

    private double evaluatePostfix(
            List<String> postfix) {

        Stack<Double> values =
                new Stack<Double>();

        for (String token : postfix) {

            /*
             * Number
             */

            if (isNumber(token)) {

                values.push(
                        Double.parseDouble(token)
                );
            }

            /*
             * Unary minus
             */

            else if (token.equals("u-")) {

                if (values.isEmpty()) {

                    throw new IllegalArgumentException(
                            "Invalid expression"
                    );
                }

                double value =
                        values.pop();

                values.push(-value);
            }

            /*
             * Binary operator
             */

            else if (isOperator(token)) {

                if (values.size() < 2) {

                    throw new IllegalArgumentException(
                            "Invalid expression"
                    );
                }

                double b =
                        values.pop();

                double a =
                        values.pop();

                double result;

                switch (token) {

                    case "+":

                        result = a + b;
                        break;

                    case "-":

                        result = a - b;
                        break;

                    case "*":

                        result = a * b;
                        break;

                    case "/":

                        if (b == 0) {

                            throw new ArithmeticException(
                                    "Cannot divide by zero"
                            );
                        }

                        result = a / b;
                        break;

                    case "%":

                        result = a % b;
                        break;

                    case "^":

                        result = Math.pow(a, b);
                        break;

                    default:

                        throw new IllegalArgumentException(
                                "Unknown operator"
                        );
                }

                values.push(result);
            }
        }

        if (values.size() != 1) {

            throw new IllegalArgumentException(
                    "Invalid expression"
            );
        }

        return values.pop();
    }


    /*
     * ==========================================
     * HELPER METHODS
     * ==========================================
     */

    private boolean isNumber(String token) {

        try {

            Double.parseDouble(token);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }


    private boolean isOperator(String token) {

        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/")
                || token.equals("%")
                || token.equals("^")
                || token.equals("u-");
    }


    private int precedence(String operator) {

        switch (operator) {

            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
            case "%":
                return 2;

            case "^":
                return 3;

            case "u-":
                return 4;

            default:
                return 0;
        }
    }
}