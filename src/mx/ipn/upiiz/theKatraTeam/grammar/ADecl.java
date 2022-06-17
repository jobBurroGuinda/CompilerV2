package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class ADecl extends Arg {

    public Type type_;
    public String id_;

    public ADecl(Type p1, String p2) {
        type_ = p1;
        id_ = p2;
    }

    @Override
    public String accept(PrintVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public CustomObject eval(EvalVisitor visitor) throws Exception  {
        return visitor.visit(this);
    }
}
