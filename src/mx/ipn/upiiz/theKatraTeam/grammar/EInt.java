package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.Visitable;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class EInt extends Exp implements Visitable {

    public Integer integer_;

    public EInt(Integer p1) {
        integer_ = p1;
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
