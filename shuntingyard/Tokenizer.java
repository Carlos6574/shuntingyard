import java.util.*;

public class Tokenizer {
    Stack<String> postfixStack;
    Stack<String> opStack;
    Constant con = new Constant();
    Operator operation = new Operator();
    public Tokenizer() {
        postfixStack = new Stack<>();
        opStack = new Stack<>();
    }

    public Stack<String> makePostfix(String expression) {
        String num = "";
        String constant = "";
        boolean decimal = false;
        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if (Character.isDigit(token) || (token == '.' && !decimal)) {
                num += Character.toString(token);

                if (token == '.') {
                    decimal = true;
                }

                if ((i+1 < expression.length()) && (Character.isDigit(expression.charAt(i+1)) || (expression.charAt(i+1) == '.' && !decimal))) {

                } else {
                    postfixStack.push(num);
                    num = "";
                }
            } else if (Character.isLetter(token)) {
                constant += Character.toString(token);
                if ((i+1 < expression.length()) && Character.isLetter(expression.charAt(i+1))) {

                } else {
                    postfixStack.push(con.getConstant(constant));
                    constant = "";
                }
            } else if (token == '(') {
                opStack.push(Character.toString(token));
            } else if (token == ')') {
                while (!"(".equals(opStack.peek())) {
                    postfixStack.push(opStack.pop());
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && (opPrecedence(Character.toString(token)) <= opPrecedence(opStack.peek()))) {
                    postfixStack.push(opStack.pop());
                }
                opStack.push(Character.toString(token));
            }
        }
        while (!opStack.isEmpty()) {
            postfixStack.push(opStack.pop());
        }
        return postfixStack;
    }

    public static int opPrecedence(String op) {
        if ("^".equals(op)) {
            return 3;
        } else if ("*".equals(op) || "/".equals(op)) {
            return 2;
        } else if ("+".equals(op) || "-".equals(op)) {
            return 1;
        }
        return -1;
    }

    public double evaluate(Stack<String> postfix) {
        Stack<String> tempStack = new Stack<>();
        System.out.println("\nTokenizer Stack Processing: ");
        for (int i = 0; i < postfix.size(); i++) {
            String token = postfix.get(i);
            if (isNum(token)){
                tempStack.push(token);
            } else {
                double b = Double.parseDouble(tempStack.pop());
                double a = Double.parseDouble(tempStack.pop());
                tempStack.push(Double.toString(operation.getAnswer(token, a, b)));
            }
            System.out.println(tempStack);
        }
        return Double.parseDouble(tempStack.pop());
    }

    public static boolean isNum(String token) {
        try {
            Double.valueOf(token);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Arithmetic Operator");
        }
        return false;
    }
}