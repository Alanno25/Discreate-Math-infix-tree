import java.util.*;

public class InfixToPrefix {

    public static List<String> infixToPrefix(List<String> tokens) {

        List<String> prefix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        Collections.reverse(tokens);

        for (String token : tokens) {

            if (isNumber(token)) {
                prefix.add(token);
            } else {

                while (!operatorStack.isEmpty() &&
                        precedence(operatorStack.peek()) > precedence(token)) {

                    prefix.add(operatorStack.pop());
                }

                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            prefix.add(operatorStack.pop());
        }

        Collections.reverse(prefix);

        return prefix;
    }

    public static boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    public static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }


    
}