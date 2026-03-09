package Infix;
import java.util.*;

public class Prefix extends Expression{

    private List<String> prefixTokens;

    public Prefix(List<String> infix){
        super(infix);
        this.prefixTokens = transform(infix);
    }

    @Override
    protected List<String> transform(List<String> tokens) {

        List<String> prefix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        Collections.reverse(tokens);

        for (String token : tokens) {

            if (ExpressionValidator.isNumber(token)) {
                prefix.add(token);
            } else {

                while (!operatorStack.isEmpty() &&
                        ExpressionValidator.precedence(operatorStack.peek()) > ExpressionValidator.precedence(token)) {

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

    public List<String> getPrefixTokens(){
        return this.prefixTokens;
    }
}