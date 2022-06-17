package mx.ipn.upiiz.visitor;


import mx.ipn.upiiz.theKatraTeam.grammar.EDiv;
import mx.ipn.upiiz.theKatraTeam.grammar.ELt;
import mx.ipn.upiiz.theKatraTeam.grammar.EEq;
import mx.ipn.upiiz.theKatraTeam.grammar.EFalse;
import mx.ipn.upiiz.theKatraTeam.grammar.EApp;
import mx.ipn.upiiz.theKatraTeam.grammar.EMinus;
import mx.ipn.upiiz.theKatraTeam.grammar.EGtEq;
import mx.ipn.upiiz.theKatraTeam.grammar.ELtEq;
import mx.ipn.upiiz.theKatraTeam.grammar.ENEq;
import mx.ipn.upiiz.theKatraTeam.grammar.SExp;
import mx.ipn.upiiz.theKatraTeam.grammar.EDecr;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeVoid;
import mx.ipn.upiiz.theKatraTeam.grammar.ADecl;
import mx.ipn.upiiz.theKatraTeam.grammar.EPIncr;
import mx.ipn.upiiz.theKatraTeam.grammar.EId;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeDouble;
import mx.ipn.upiiz.theKatraTeam.grammar.SDecls;
import mx.ipn.upiiz.theKatraTeam.grammar.EString;
import mx.ipn.upiiz.theKatraTeam.grammar.SReturn;
import mx.ipn.upiiz.theKatraTeam.grammar.EDouble;
import mx.ipn.upiiz.theKatraTeam.grammar.EOr;
import mx.ipn.upiiz.theKatraTeam.grammar.EAnd;
import mx.ipn.upiiz.theKatraTeam.grammar.EPlus;
import mx.ipn.upiiz.theKatraTeam.grammar.EIncr;
import mx.ipn.upiiz.theKatraTeam.grammar.ETrue;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeInt;
import mx.ipn.upiiz.theKatraTeam.grammar.EInt;
import mx.ipn.upiiz.theKatraTeam.grammar.PDefs;
import mx.ipn.upiiz.theKatraTeam.grammar.EPDecr;
import mx.ipn.upiiz.theKatraTeam.grammar.DFun;
import mx.ipn.upiiz.theKatraTeam.grammar.SAss;
import mx.ipn.upiiz.theKatraTeam.grammar.SWhile;
import mx.ipn.upiiz.theKatraTeam.grammar.SInit;
import mx.ipn.upiiz.theKatraTeam.grammar.EGt;
import mx.ipn.upiiz.theKatraTeam.grammar.EAss;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeString;
import mx.ipn.upiiz.theKatraTeam.grammar.SIfElse;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeBool;
import mx.ipn.upiiz.theKatraTeam.grammar.ETimes;

public interface PrintVisitor {

    String visit(PDefs pDefs);

    String visit(DFun dFun);

    String visit(SReturn sReturn);

    String visit(SDecls sDecls);

    String visit(ADecl aDecl);

    String visit(SInit sInit);

    String visit(SAss sAss);

    String visit(SExp sExp);

    String visit(SIfElse sIfElse);

    String visit(SWhile sWhile);

    String visit(EId eId);

    String visit(EIncr eIncr);

    String visit(EPIncr epIncr);

    String visit(EDecr eDecr);

    String visit(EPDecr epDecr);

    String visit(EInt eInt);

    String visit(ETrue eTrue);

    String visit(EFalse eFalse);

    String visit(EDouble eDouble);

    String visit(EString eString);

    String visit(EEq eEq);

    String visit(ENEq enEq);

    String visit(EGt eGt);

    String visit(EGtEq eGtEq);

    String visit(ELt eLt);

    String visit(ELtEq eLtEq);

    String visit(EAnd eAnd);

    String visit(EOr eOr);

    String visit(EApp eApp);

    String visit(EAss eAss);

    String visit(EPlus ePlus);

    String visit(EMinus eMinus);

    String visit(EDiv eDiv);

    String visit(ETimes eTimes);

    String visit(TypeBool typeBool);

    String visit(TypeInt typeInt);

    String visit(TypeDouble typeDouble);

    String visit(TypeString typeString);

    String visit(TypeVoid typeVoid);

}
