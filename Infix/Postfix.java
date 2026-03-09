package Infix;
import java.util.*;

public class Postfix extends Expression{

    private List<String> postfixTokens;

    public Postfix(List<String> infix){
        super(infix);
        this.postfixTokens = transform(infix);
    }

    @Override
    protected List<String> transform(List<String> tokens) {

        List<String> postfix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {

            if (ExpressionValidator.isNumber(token)) {
                postfix.add(token);
            } else {

                while (!operatorStack.isEmpty() &&
                        ExpressionValidator.precedence(operatorStack.peek()) >= ExpressionValidator.precedence(token)) {

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

    public List<String> getPostfixTokens(){
        return this.postfixTokens;
    }

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     String input = sc.nextLine();
    //     String[] tokens = input.split(" ");
    //     List<String> infix = Arrays.asList(tokens);
    //     List<String> postfix = infixToPostfix(infix);
    //     System.out.println(postfix);
    //     sc.close();
    // }
}