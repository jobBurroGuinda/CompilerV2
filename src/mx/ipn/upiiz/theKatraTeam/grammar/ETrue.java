package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class ETrue extends Exp {

    public ETrue() { }

    @Override
    public String accept(PrintVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public CustomObject eval(EvalVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
