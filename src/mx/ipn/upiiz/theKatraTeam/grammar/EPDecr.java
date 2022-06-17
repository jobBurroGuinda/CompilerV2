package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class EPDecr extends Exp {

    public Exp exp_;

    public EPDecr(Exp p1) {
        exp_ = p1;
    }

    @Override
    public String accept(PrintVisitor visitor) {
        return null;
    }

    @Override
    public CustomObject eval(EvalVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
