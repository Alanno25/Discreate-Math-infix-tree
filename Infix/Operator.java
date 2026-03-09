package Infix;

public enum Operator {
    PLUS("+", 1) {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    MINUS("-", 1) {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLE("*", 2) {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE("/", 2){
        @Override
        public double apply(double a, double b) {
            return a / b;
        }
    };

    private String operator;
    private int precedence;

    private Operator(String opr, int pcd) {
        this.operator = opr;
        this.precedence = pcd;
    }

    public abstract double apply(double a, double b);

    public String getOperator(){
        return this.operator;
    }

    public int getPrecedence(){
        return this.precedence;
    }

    public static int operatorToPrecedence(String opr){
        for (Operator i : Operator.values()){
            if (opr.equals(i.getOperator())){
                return i.getPrecedence();
            }
        }
        return 0;
    }

    public static Operator getOperator(String opr) {
        for (Operator i : Operator.values()){
            if (opr.equals(i.getOperator())){
                return i;
            }
        }
        return null;
    }
}
