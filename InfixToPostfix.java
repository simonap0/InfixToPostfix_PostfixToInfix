package Project3_InfixToPostfix_PostfixToInfix;

import java.util.*;

public class InfixToPostfix {

    public static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' ;
    }

    public static int operatorPrecedence (char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }else if (ch == '*' || ch == '/') {
            return 2;
        }else if (ch == '^') {
            return 3;
        }else
            return -1;
    }

    public static boolean isOperand (char ch) {
        return Character.isDigit(ch);
    }

    public static String infixToPostfix(String str) {
        Stack<Character> stack = new Stack<Character>();

        String postfixForm = "";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (isOperand(c)) {
                postfixForm += c;
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '('
                        && (
                                operatorPrecedence(c) < operatorPrecedence(stack.peek())
                                || (
                                        operatorPrecedence(c) == operatorPrecedence(stack.peek())
                                        && (operatorPrecedence((stack.peek())) == 1 || operatorPrecedence((stack.peek())) == 2)
                                )
                        )
                ) {
                    postfixForm += stack.pop();
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    postfixForm += stack.pop();
                }

                if (stack.peek() != '(' && stack.isEmpty() ) {
                    return "expresia avea paranteze gresite";
                }

                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            postfixForm += stack.pop();
            if (!stack.isEmpty() && stack.peek() == '(') {
                return "expresia avea paranteze gresite";
            }
        }

        return postfixForm;

    }

    public static void main(String[] args) {

        String str = "3+(2+1)*2^3^2-8/(5-1*2/2)";

        System.out.println(infixToPostfix(str));

    }

}
