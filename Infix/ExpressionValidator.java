package Infix;

public class ExpressionValidator {
    public static boolean isNumber(String token) {
        return token.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

    public static boolean isOperator(String opr){
        for (Operator i : Operator.values()){
            if (opr.equals(i.getOperator())){
                return true;
            }
        }
        return false;
    }

    public static int precedence(String op) {
        if (isOperator(op)) {
            return Operator.operatorToPrecedence(op);
        }
        return 0;
    }
}
