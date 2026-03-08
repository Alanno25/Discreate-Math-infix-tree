import java.util.*;

public class InfixToPostfix {

    public static List<String> infixToPostfix(List<String> tokens) {

        List<String> postfix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {

            if (isNumber(token)) {
                postfix.add(token);
            } else {

                while (!operatorStack.isEmpty() &&
                        precedence(operatorStack.peek()) >= precedence(token)) {

                    postfix.add(operatorStack.pop());
                }

                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.add(operatorStack.pop());
        }

        return postfix;
    }

    public static boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    public static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] tokens = input.split(" ");

        List<String> infix = Arrays.asList(tokens);

        List<String> postfix = infixToPostfix(infix);

        System.out.println(postfix);

        sc.close();
    }
}