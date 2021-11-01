package Project3_InfixToPostfix_PostfixToInfix;

import java.util.Stack;

public class PostfixToInfix {
    public static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    public static boolean isOperand (char ch) {
        return Character.isDigit(ch);
    }

    public static int postfixToInfix(String str) {
        Stack<Integer> stack = new Stack<Integer>();

        String finalForm = "";
        char c = ' ';
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (isOperand(c)) {
                stack.push(Integer.parseInt(Character.toString(c)));
            } else if (isOperator(c)){
                if (stack.isEmpty()){
                    System.out.println("expresia postfixata este gresita");
                    return -1;
                }
                int op1 = stack.pop();

                if (stack.isEmpty()){
                    System.out.println("expresia postfixata este gresita");
                    return -1;
                }
                int op2 = stack.pop();


                if (c == '+'){
                    result  = op2 + op1;
                } else if (c == '-') {
                    result  = op2 - op1;
                } else if (c == '*') {
                    result  = op2 * op1;
                } else if (c == '/') {
                    result  = op2 / op1;
                } else if (c == '^') {
                    result = (int)Math.pow(op2, op1);
                }

                finalForm += "(" + op2 + c + op1 + ")";
                stack.push(result);
            }
        }

        result = stack.pop();

        if (!stack.isEmpty()) {
            System.out.println("expresia postfixata este gresita");
            return -1;
        }

        System.out.println(finalForm);

        return result;
    }

    public static void main(String[] args) {

        String str2 = "321+232^^*+8512*2/-/-";

        System.out.println(postfixToInfix(str2));

    }

}
