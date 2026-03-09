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
            if (ExpressionValidator.isOperator(token)) {
                 while (!operatorStack.isEmpty() &&
                        ExpressionValidator.precedence(operatorStack.peek()) >= ExpressionValidator.precedence(token)) {

                    postfix.add(operatorStack.pop());
                }

                operatorStack.push(token);
            } else {
                postfix.add(token);
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

    @Override
    public String toString(){
       String res = "";
        for (String token : postfixTokens) {
            res += token + " ";
        }
        return res;
    }
}