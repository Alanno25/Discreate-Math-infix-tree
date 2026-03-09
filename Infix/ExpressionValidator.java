package Infix;

public class ExpressionValidator {
    public static boolean isNumber(String token) {
        return token.matches("\\d+");
    }

    public static int precedence(String op) {
        if (Operator.isOperator(op)) {
            return Operator.operatorToPrecedence(op);
        }
        return 0;
    }
}
