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
        List<String> copyTokens = new ArrayList<>(tokens);

        Collections.reverse(copyTokens);

        for (String token : copyTokens) {
            if (ExpressionValidator.isOperator(token)) {
                 while (!operatorStack.isEmpty() &&
                        ExpressionValidator.precedence(operatorStack.peek()) >= ExpressionValidator.precedence(token)) {

                    prefix.add(operatorStack.pop());
                }
                operatorStack.push(token);
            } else {
                prefix.add(token);
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

    @Override
    public String toString(){
        String res = "";
        for (String token : prefixTokens) {
            res += token + " ";
        }

        return res;
    }
}