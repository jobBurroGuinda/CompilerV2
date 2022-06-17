package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.Visitable;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class EPlus extends Exp implements Visitable {

    public Exp exp_1, exp_2;

    public EPlus(Exp p1, Exp p2) {
        exp_1 = p1;
        exp_2 = p2;
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
