package mx.ipn.upiiz.visitor;

import mx.ipn.upiiz.theKatraTeam.grammar.CustomObject;

public interface Visitable {

    String accept(PrintVisitor visitor);

    CustomObject eval(EvalVisitor visitor) throws Exception;

}
