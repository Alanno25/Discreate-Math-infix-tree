package Infix;

import java.util.Arrays;
import java.util.List;

public class ExpressionParser {
    public static List<String> toInfixTokens(String str){
        return Arrays.asList(str.split(" "));
    }

    public static boolean isExpressionValid(List<String> exp){
        for (String opr : exp) {
            if (!ExpressionValidator.isNumber(opr)
                && !ExpressionValidator.isOperator(opr)) {
                return false;
            }
        }
        return true;
    }
}
