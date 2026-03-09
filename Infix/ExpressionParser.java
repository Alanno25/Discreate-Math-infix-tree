package Infix;

import java.util.Arrays;
import java.util.List;

public class ExpressionParser {
    public static List<String> toInfixTokens(String str){
        return Arrays.asList(str.split(" "));
    }

    public static boolean isExpressionValid(List<String> exp){
        int numOperator = 0;
        int numOperand = 0;
        for (String opr : exp) {
            boolean isNum = ExpressionValidator.isNumber(opr);
            boolean isOpr = ExpressionValidator.isOperator(opr);
            boolean isVar = ExpressionValidator.isVariable(opr);
            
            if (!isNum && !isOpr && !isVar) {
                return false;
            }
            if (isNum || isVar) {
                numOperand += 1;
            }
            else {
                numOperator += 1;
            }
        }

        if (ExpressionValidator.isOperator(exp.getLast())) return false;
        if (numOperator != numOperand - 1) return false;

        return true;
    }
}
