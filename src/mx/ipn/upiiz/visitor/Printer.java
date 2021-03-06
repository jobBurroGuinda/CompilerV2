package mx.ipn.upiiz.visitor;

import mx.ipn.upiiz.theKatraTeam.grammar.EDiv;
import mx.ipn.upiiz.theKatraTeam.grammar.ELt;
import mx.ipn.upiiz.theKatraTeam.grammar.EEq;
import mx.ipn.upiiz.theKatraTeam.grammar.EFalse;
import mx.ipn.upiiz.theKatraTeam.grammar.EApp;
import mx.ipn.upiiz.theKatraTeam.grammar.Def;
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
import mx.ipn.upiiz.theKatraTeam.grammar.Stm;
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

public class Printer implements PrintVisitor {

    @Override
    public String visit(PDefs pDefs) {

        StringBuilder builder = new StringBuilder();
        builder.append("Programa: ");

        for (Def def : pDefs.listdef_) {
            builder.append(def.accept(this));
        }

        return builder.toString();
    }


    @Override
    public String visit(DFun dFun) {

        StringBuilder builder = new StringBuilder();

        builder.append("DFuncion: ");

        for (Stm stm : dFun.liststm_) {
            builder.append(stm.accept(this));
        }

        return builder.toString();
    }

    @Override
    public String visit(SReturn sReturn) {

        StringBuilder builder = new StringBuilder();

        builder.append("SRegresa: ");
        builder.append(sReturn.exp_.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(SDecls sDecls) {
        return "SDeclaraciones: " + sDecls.id_;
    }

    @Override
    public String visit(ADecl aDecl) {
        return null;
    }

    @Override
    public String visit(SInit sInit) {

        StringBuilder builder = new StringBuilder();

        builder.append("SInit: ");
        builder.append(sInit.type_.accept(this));
        builder.append(sInit.id_);
        builder.append(sInit.exp_.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(SAss sAss) {
        StringBuilder builder = new StringBuilder();

        builder.append("SAss: ");
        builder.append(sAss.id);
        builder.append(sAss.exp.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(SExp sExp) {

        return "SExpresion: " + sExp.exp_.accept(this);
    }

    @Override
    public String visit(SIfElse sIfElse) {

        StringBuilder builder = new StringBuilder();

        builder.append("SJaNein: ");
        builder.append(sIfElse.exp_.accept(this));
        for (Stm stm : sIfElse.stm_1) {
            builder.append(stm.accept(this));
        }
        for (Stm stm : sIfElse.stm_2) {
            builder.append(stm.accept(this));
        }

        return builder.toString();
    }

    @Override
    public String visit(SWhile sWhile) {
        StringBuilder builder = new StringBuilder();

        builder.append("SWhile: ");
        builder.append(sWhile.exp_.accept(this));

        for (Stm stm : sWhile.stm_) {
            builder.append(stm.accept(this));
        }

        return builder.toString();
    }

    @Override
    public String visit(EId eId) {
        return "EId: " + eId.id_;
    }

    @Override
    public String visit(EIncr eIncr) {
        return "EIncr: " + eIncr.exp_.accept(this);
    }

    @Override
    public String visit(EPIncr epIncr) {
        return null;
    }

    @Override
    public String visit(EDecr eDecr) {
        return "EDecr: " + eDecr.exp_.accept(this);
    }

    @Override
    public String visit(EPDecr epDecr) {
        return null;
    }

    @Override
    public String visit(EInt eInt) {
        return "EEnt: " + eInt.integer_.toString();
    }

    @Override
    public String visit(ETrue eTrue) {
        return "ETrue: true";
    }

    @Override
    public String visit(EFalse eFalse) {
        return "EFalse: false ";
    }

    @Override
    public String visit(EDouble eDouble) {
        return "EDecimal: " + eDouble.double_.toString();
    }

    @Override
    public String visit(EString eString) {
        return "ECadena: " + eString.string_;
    }

    @Override
    public String visit(EEq eEq) {
        StringBuilder builder = new StringBuilder();

        builder.append("EEq: ");
        builder.append(eEq.exp_1.accept(this));
        builder.append(eEq.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(ENEq enEq) {
        StringBuilder builder = new StringBuilder();

        builder.append("ENeq: ");
        builder.append(enEq.exp_1.accept(this));
        builder.append(enEq.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EGt eGt) {
        StringBuilder builder = new StringBuilder();

        builder.append("EGt: ");
        builder.append(eGt.exp_1.accept(this));
        builder.append(eGt.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EGtEq eGtEq) {
        StringBuilder builder = new StringBuilder();

        builder.append("EGtEq: ");
        builder.append(eGtEq.exp_1.accept(this));
        builder.append(eGtEq.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(ELt eLt) {
        StringBuilder builder = new StringBuilder();

        builder.append("ELt: ");
        builder.append(eLt.exp_1.accept(this));
        builder.append(eLt.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(ELtEq eLtEq) {
        StringBuilder builder = new StringBuilder();

        builder.append("EltEq: ");
        builder.append(eLtEq.exp_1.accept(this));
        builder.append(eLtEq.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EAnd eAnd) {
        StringBuilder builder = new StringBuilder();

        builder.append("EAnd: ");
        builder.append(eAnd.exp_1.accept(this));
        builder.append(eAnd.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EOr eOr) {
        StringBuilder builder = new StringBuilder();

        builder.append("EOr: ");
        builder.append(eOr.exp_1.accept(this));
        builder.append(eOr.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EApp eApp) {
        return null;
    }

    @Override
    public String visit(EAss eAss) {
        return null;
    }

    @Override
    public String visit(EPlus ePlus) {

        StringBuilder builder = new StringBuilder();

        builder.append("EMas: ");
        builder.append(ePlus.exp_1.accept(this));
        builder.append(ePlus.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EMinus eMinus) {
        StringBuilder builder = new StringBuilder();

        builder.append("EMenos: ");
        builder.append(eMinus.exp_1.accept(this));
        builder.append(eMinus.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(EDiv eDiv) {
        StringBuilder builder = new StringBuilder();

        builder.append("EDiv: ");
        builder.append(eDiv.exp_1.accept(this));
        builder.append(eDiv.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(ETimes eTimes) {
        StringBuilder builder = new StringBuilder();

        builder.append("ETimes: ");
        builder.append(eTimes.exp_1.accept(this));
        builder.append(eTimes.exp_2.accept(this));

        return builder.toString();
    }

    @Override
    public String visit(TypeBool typeBool) {
        return "TypeBool";
    }

    @Override
    public String visit(TypeInt typeInt) {
        return "TypeInt";
    }

    @Override
    public String visit(TypeDouble typeDouble) {
        return "TypeDouble";
    }

    @Override
    public String visit(TypeString typeString) {
        return "TypeString";
    }

    @Override
    public String visit(TypeVoid typeVoid) {
        return "TypeVoid";
    }
}
