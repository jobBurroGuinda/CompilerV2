package mx.ipn.upiiz.visitor;


import mx.ipn.upiiz.theKatraTeam.grammar.EDiv;
import mx.ipn.upiiz.theKatraTeam.grammar.ELt;
import mx.ipn.upiiz.theKatraTeam.grammar.EEq;
import mx.ipn.upiiz.theKatraTeam.grammar.EFalse;
import mx.ipn.upiiz.theKatraTeam.grammar.EApp;
import mx.ipn.upiiz.theKatraTeam.grammar.EMinus;
import mx.ipn.upiiz.theKatraTeam.grammar.CustomObject;
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

public interface EvalVisitor {

    CustomObject visit(PDefs pDefs) throws Exception;

    CustomObject visit(DFun dFun) throws Exception;

    CustomObject visit(SReturn sReturn) throws Exception;

    CustomObject visit(SDecls sDecls) throws Exception;

    CustomObject visit(ADecl aDecl) throws Exception;

    CustomObject visit(SInit sInit) throws Exception;

    CustomObject visit(SAss sAss) throws Exception;

    CustomObject visit(SExp sExp) throws Exception;

    CustomObject visit(SIfElse sIfElse) throws Exception;

    CustomObject visit(SWhile sWhile) throws Exception;

    CustomObject visit(EId eId) throws Exception;

    CustomObject visit(EIncr eIncr) throws Exception;

    CustomObject visit(EPIncr epIncr) throws Exception;

    CustomObject visit(EDecr eDecr) throws Exception;

    CustomObject visit(EPDecr epDecr) throws Exception;

    CustomObject visit(EInt eInt) throws Exception;

    CustomObject visit(ETrue eTrue) throws Exception;

    CustomObject visit(EFalse eFalse) throws Exception;

    CustomObject visit(EDouble eDouble) throws Exception;

    CustomObject visit(EString eString) throws Exception;

    CustomObject visit(EEq eEq) throws Exception;

    CustomObject visit(ENEq enEq) throws Exception;

    CustomObject visit(EGt eGt) throws Exception;

    CustomObject visit(EGtEq eGtEq) throws Exception;

    CustomObject visit(ELt eLt) throws Exception;

    CustomObject visit(ELtEq eLtEq) throws Exception;

    CustomObject visit(EAnd eAnd) throws Exception;

    CustomObject visit(EOr eOr) throws Exception;

    CustomObject visit(EApp eApp) throws Exception;

    CustomObject visit(EAss eAss) throws Exception;

    CustomObject visit(EPlus ePlus) throws Exception;

    CustomObject visit(EMinus eMinus) throws Exception;

    CustomObject visit(EDiv eDiv) throws Exception;

    CustomObject visit(ETimes eTimes) throws Exception;

    CustomObject visit(TypeBool typeBool) throws Exception;

    CustomObject visit(TypeInt typeInt) throws Exception;

    CustomObject visit(TypeDouble typeDouble) throws Exception;

    CustomObject visit(TypeString typeString) throws Exception;

    CustomObject visit(TypeVoid typeVoid) throws Exception;

}
