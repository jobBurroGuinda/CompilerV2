package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class SIfElse extends Stm {

    public Exp exp_;

    public ListStm stm_1, stm_2;

    public SIfElse(Exp p1, ListStm p2, ListStm p3) {
        exp_ = p1;
        stm_1 = p2;
        stm_2 = p3;
    }

    @Override
    public String accept(PrintVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public CustomObject eval(EvalVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
