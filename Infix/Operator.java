package Infix;

public enum Operator {
    PLUS("+", 1),
    MINUS("-", 1),
    MULTIPLE("*", 2),
    DIVIDE("/", 2);

    private String operator;
    private int precedence;

    private Operator(String opr, int pcd) {
        this.operator = opr;
        this.precedence = pcd;
    }

    public String getOperator(){
        return this.operator;
    }

    public int getPrecedence(){
        return this.precedence;
    }

    public static boolean isOperator(String opr){
        for (Operator i : Operator.values()){
            if (!opr.equals(i.getOperator())){
                return false;
            }
        }
        return true;
    }

    public static int operatorToPrecedence(String opr){
        for (Operator i : Operator.values()){
            if (opr.equals(i.getOperator())){
                return i.getPrecedence();
            }
        }
        return 0;
    }
}
