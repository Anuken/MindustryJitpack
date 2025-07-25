package mindustry.logic;

import arc.util.*;

public enum ConditionOp{
    equal("==", (a, b) -> Math.abs(a - b) < 0.000001, Structs::eq),
    notEqual("not", (a, b) -> Math.abs(a - b) >= 0.000001, (a, b) -> !Structs.eq(a, b)),
    lessThan("<", (a, b) -> a < b),
    lessThanEq("<=", (a, b) -> a <= b),
    greaterThan(">", (a, b) -> a > b),
    greaterThanEq(">=", (a, b) -> a >= b),
    strictEqual("===", (a, b) -> false),
    always("always", (a, b) -> true);

    public static final ConditionOp[] all = values();

    public final CondObjOpLambda objFunction;
    public final CondOpLambda function;
    public final String symbol;

    ConditionOp(String symbol, CondOpLambda function){
        this(symbol, function, null);
    }

    ConditionOp(String symbol, CondOpLambda function, CondObjOpLambda objFunction){
        this.symbol = symbol;
        this.function = function;
        this.objFunction = objFunction;
    }

    public boolean test(LVar va, LVar vb){
        if(this == ConditionOp.strictEqual){
            return va.isobj == vb.isobj && ((va.isobj && va.objval == vb.objval) || (!va.isobj && va.numval == vb.numval));
        }
        if(objFunction != null && va.isobj && vb.isobj){
            //use object function if both are objects
            return objFunction.get(va.obj(), vb.obj());
        }
        return function.get(va.num(), vb.num());
    }

    @Override
    public String toString(){
        return symbol;
    }

    interface CondObjOpLambda{
        boolean get(Object a, Object b);
    }

    interface CondOpLambda{
        boolean get(double a, double b);
    }
}
