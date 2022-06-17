package mx.ipn.upiiz.theKatraTeam.grammar;

import mx.ipn.upiiz.visitor.EvalVisitor;
import mx.ipn.upiiz.visitor.PrintVisitor;

public class EApp extends Exp {

    public String id_;
    public ListExp listexp_;

    public EApp(String p1, ListExp p2) {
        id_ = p1;
        listexp_ = p2;
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
