package Infix;

import java.util.List;
import java.util.Stack;

public class CalculateExpresstion {

    public static void calculateExpression(Postfix postfix){
        List<String> postfixTokens = postfix.getPostfixTokens();
        Stack<String> stack = new Stack<>();
        int orderNumber = 1;

        System.out.println(postfix.getInfixString());

        for (String opr : postfixTokens) {
            if (ExpressionValidator.isOperator(opr)) {
                String secondOperand = stack.pop();
                String firstOperand = stack.pop();

                System.out.println("\n==============================");
                System.out.println("Step " + orderNumber++);
                System.out.println("The first operand is " + firstOperand);
                System.out.println("The second operand is " + secondOperand);
                System.out.println("The operator is " + opr);

                String result;

                if (ExpressionValidator.isNumber(firstOperand)
                    && ExpressionValidator.isNumber(secondOperand)) {
                        double firstNumber = Double.parseDouble(firstOperand);
                        double secondNumber = Double.parseDouble(secondOperand);

                        Operator operator = Operator.getOperator(opr);

                        double numberResult = Math.round(operator.apply(firstNumber, secondNumber) * 100.0) / 100.0;
                        result = String.valueOf(numberResult);
                }
                else {
                    result = firstOperand + " " + opr + " " + secondOperand;
                }

                System.out.println("The result is " + result);
                System.out.println("And then we put the result in the stack");
                stack.push(result);
            }
            else {
                stack.push(opr);
            }
        }

        System.out.println("\n==============================");
        System.out.print("The final result is ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        System.out.println("==============================\n");
    }
}
