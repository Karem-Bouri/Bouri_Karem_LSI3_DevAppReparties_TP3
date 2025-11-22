package Tp3_2;

import java.io.Serializable;

public class Operation implements Serializable {
    private double a;
    private double b;
    private String op;  // "+", "-", "*", "/"

    public Operation(double a, double b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public String getOp() { return op; }
}

