package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class EIncr extends Exp {

    public Exp exp_;

    public EIncr(Exp p1) {
        exp_ = p1;
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
