package Infix;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input your expression");
        System.out.println("using only these symbols: + - * /");
        System.out.println("example input: a + b - c * d / g");

        String input = sc.nextLine();

        List<String> tokens = ExpressionParser.toInfixTokens(input);
        if (!ExpressionParser.isExpressionValid(tokens)) {
            System.out.println("your input is not valid");
            sc.close();
            return;
        }

        boolean isReadyToExit = false;

        while (!isReadyToExit) {
            System.out.println("Select menu");
            System.out.println("1: Prefix form");
            System.out.println("2: Postfix form");
            System.out.println("3: Calculate");
            System.out.println("4: Exit");

            int mode = sc.nextInt();

            switch (mode) {
                case 1:
                    System.out.println("==================================================");
                    System.out.println("\t\tInfix To Prefix");
                    System.out.println("==================================================");
                    Prefix prefix = new Prefix(tokens);

                    System.out.println(prefix + "\n");
                    break;
                case 2:
                    System.out.println("==================================================");
                    System.out.println("\t\tInfix To Postfix");
                    System.out.println("==================================================");
                    Postfix postfix = new Postfix(tokens);

                    System.out.println(postfix + "\n");
                    break;
                case 3:
                    System.out.println("==================================================");
                    System.out.println("\t\tCalculate Expression");
                    System.out.println("==================================================");
                    CalculateExpresstion.calculateExpression(new Postfix(tokens));
                    break;
                case 4:
                    isReadyToExit = true;
                    break;
            }
        }

        sc.close();
    }
}
