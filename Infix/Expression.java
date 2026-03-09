package Infix;

import java.util.List;

public abstract class Expression {

    private List<String> infixTokens;

    public Expression(List<String> infix){
        this.infixTokens = infix;
    }

    protected abstract List<String> transform(List<String> tokens);

    public List<String> getInfixTokens(){
        return this.infixTokens;
    }
}
