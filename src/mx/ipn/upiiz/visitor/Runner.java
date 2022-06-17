package mx.ipn.upiiz.visitor;

import mx.ipn.upiiz.theKatraTeam.grammar.EDiv;
import mx.ipn.upiiz.theKatraTeam.grammar.ELt;
import mx.ipn.upiiz.theKatraTeam.grammar.EEq;
import mx.ipn.upiiz.theKatraTeam.grammar.EFalse;
import mx.ipn.upiiz.theKatraTeam.grammar.EApp;
import mx.ipn.upiiz.theKatraTeam.grammar.Def;
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
import mx.ipn.upiiz.theKatraTeam.grammar.Stm;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeDouble;
import mx.ipn.upiiz.theKatraTeam.grammar.SDecls;
import mx.ipn.upiiz.theKatraTeam.grammar.EString;
import mx.ipn.upiiz.theKatraTeam.grammar.Type;
import mx.ipn.upiiz.theKatraTeam.grammar.SReturn;
import mx.ipn.upiiz.theKatraTeam.grammar.EDouble;
import mx.ipn.upiiz.theKatraTeam.grammar.EOr;
import mx.ipn.upiiz.theKatraTeam.grammar.EAnd;
import mx.ipn.upiiz.theKatraTeam.grammar.EPlus;
import mx.ipn.upiiz.theKatraTeam.grammar.EIncr;
import mx.ipn.upiiz.theKatraTeam.grammar.ETrue;
import mx.ipn.upiiz.theKatraTeam.grammar.EInt;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeInt;
import mx.ipn.upiiz.theKatraTeam.grammar.PDefs;
import mx.ipn.upiiz.theKatraTeam.grammar.EPDecr;
import mx.ipn.upiiz.theKatraTeam.grammar.DFun;
import mx.ipn.upiiz.theKatraTeam.grammar.SAss;
import mx.ipn.upiiz.theKatraTeam.grammar.SWhile;
import mx.ipn.upiiz.theKatraTeam.grammar.SInit;
import mx.ipn.upiiz.theKatraTeam.grammar.EGt;
import mx.ipn.upiiz.theKatraTeam.grammar.EAss;
import mx.ipn.upiiz.theKatraTeam.grammar.SIfElse;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeString;
import mx.ipn.upiiz.theKatraTeam.grammar.TypeBool;
import mx.ipn.upiiz.theKatraTeam.grammar.ETimes;

import java.util.HashMap;
import java.util.Map;

public class Runner implements EvalVisitor {

    public Map<String, CustomObject> GLOBAL_SCOPE;

    public Runner() {
        GLOBAL_SCOPE = new HashMap<>();
    }

    @Override
    public CustomObject visit(PDefs pDefs) throws Exception {

        CustomObject res = null;

        for (Def def : pDefs.listdef_) {
            res = def.eval(this);
        }

        return res;
    }

    @Override
    public CustomObject visit(DFun dFun) throws Exception {

        CustomObject res = null;

        for (Stm stm : dFun.liststm_) {
            res = stm.eval(this);
        }

        return res;
    }

    @Override
    public CustomObject visit(SReturn sReturn) throws Exception {
        return sReturn.exp_.eval(this);
    }

    @Override
    public CustomObject visit(SDecls sDecls) throws Exception {
        if (!GLOBAL_SCOPE.containsKey(sDecls.id_)) {
            String variable = sDecls.id_;
            Type type = sDecls.type_;
            GLOBAL_SCOPE.put(variable, new CustomObject(type, null));
        } else {
            throw new Exception("La variable " + sDecls.id_ + " ya existe!");
        }
        return null;
    }

    @Override
    public CustomObject visit(ADecl aDecl) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(SInit sInit) throws Exception {

        if (!GLOBAL_SCOPE.containsKey(sInit.id_)) {

            String variable = sInit.id_;

            CustomObject value = sInit.exp_.eval(this);

            Type type = sInit.type_;

            if (value.type instanceof TypeBool && type instanceof TypeBool) {

            } else if (value.type instanceof TypeInt && type instanceof TypeInt) {

            } else if (value.type instanceof TypeDouble && type instanceof TypeDouble) {

            } else if (value.type instanceof TypeString && type instanceof TypeString) {

            } else {
                throw new Exception("Type error: Intentando asignar " + value.type + " a " + type);
            }

            GLOBAL_SCOPE.put(variable, new CustomObject(type, value));

        } else {
            throw new Exception("La variable " + sInit.id_ + " ya había sido asignada!");
        }

        return null;

    }

    @Override
    public CustomObject visit(SAss sAss) throws Exception {

        if (GLOBAL_SCOPE.containsKey(sAss.id)) {

            String variable = sAss.id;

            CustomObject value = sAss.exp.eval(this);

            Type type = GLOBAL_SCOPE.get(variable).type;

            if (value.type instanceof TypeBool && type instanceof TypeBool) {

            } else if (value.type instanceof TypeInt && type instanceof TypeInt) {

            } else if (value.type instanceof TypeDouble && type instanceof TypeDouble) {

            } else if (value.type instanceof TypeString && type instanceof TypeString) {

            } else {
                throw new Exception("Type error: intentando asignar " + value.type.getClass().getName() + " a " + type.getClass().getName());
            }

            GLOBAL_SCOPE.put(variable, new CustomObject(type, value));

        } else {
            throw new Exception("La variable " + sAss.id + " no ha sido declarada!");
        }

        return null;

    }

    @Override
    public CustomObject visit(SExp sExp) throws Exception {
        return sExp.exp_.eval(this);
    }

    @Override
    public CustomObject visit(SIfElse sIfElse) throws Exception {

        CustomObject condition = sIfElse.exp_.eval(this);

        CustomObject res = null;

        if (!(condition.type instanceof TypeBool))
            throw new Exception("Type error: la condición no es booleana");

        if (Boolean.parseBoolean(condition.value.toString())) {
            for (Stm stm : sIfElse.stm_1) {
                res = stm.eval(this);
            }
        } else {
            for (Stm stm : sIfElse.stm_2) {
                res = stm.eval(this);
            }
        }

        return res;
    }

    @Override
    public CustomObject visit(SWhile sWhile) throws Exception {

        CustomObject condition = sWhile.exp_.eval(this);

        CustomObject res = null;

        if (!(condition.type instanceof TypeBool))
            throw new Exception("Type error: la condición no es booleana");

        if (Boolean.parseBoolean(condition.value.toString())) {
            for (Stm stm : sWhile.stm_) {
                res = stm.eval(this);
            }
            sWhile.eval(this);
        }

        return res;
    }

    @Override
    public CustomObject visit(EId eId) throws Exception {

        if (GLOBAL_SCOPE.containsKey(eId.id_) && GLOBAL_SCOPE.get(eId.id_).value != null) {
            return GLOBAL_SCOPE.get(eId.id_);
        } else if (GLOBAL_SCOPE.containsKey(eId.id_) && GLOBAL_SCOPE.get(eId.id_).value == null) {
            throw new Exception("La variable " + eId.id_ + " nunca fue inicializada!");
        } else {
            throw new Exception("La variable " + eId.id_ + " no existe!");
        }

    }

    @Override
    public CustomObject visit(EIncr eIncr) throws Exception {

        CustomObject value = eIncr.exp_.eval(this);

        if (value.type instanceof TypeInt) {

            value.value = Integer.parseInt(value.value.toString()) + 1;
            if (eIncr.exp_ instanceof EId) {
                GLOBAL_SCOPE.put(((EId) eIncr.exp_).id_, value);
                return null;
            }
            return value;

        } else {
            throw new Exception("Type error: Sólo se aceptan datos numéricos!");
        }
    }

    @Override
    public CustomObject visit(EPIncr epIncr) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(EDecr eDecr) throws Exception {

        CustomObject value = eDecr.exp_.eval(this);

        if (value.type instanceof TypeInt) {

            value.value = Integer.parseInt(value.value.toString()) - 1;
            if (eDecr.exp_ instanceof EId) {
                GLOBAL_SCOPE.put(((EId) eDecr.exp_).id_, value);
                return null;
            }
            return value;

        } else {
            throw new Exception("Type error: Sólo se aceptan datos numéricos!");
        }
    }

    @Override
    public CustomObject visit(EPDecr epDecr) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(EInt eInt) throws Exception {
        return new CustomObject(new TypeInt(), eInt.integer_);
    }

    @Override
    public CustomObject visit(ETrue eTrue) throws Exception {
        return new CustomObject(new TypeBool(), true);
    }

    @Override
    public CustomObject visit(EFalse eFalse) throws Exception {
        return new CustomObject(new TypeBool(), false);
    }

    @Override
    public CustomObject visit(EDouble eDouble) throws Exception {
        return new CustomObject(new TypeDouble(), eDouble.double_);
    }

    @Override
    public CustomObject visit(EString eString) throws Exception {
        return new CustomObject(new TypeString(), eString.string_.replace("\"", ""));
    }

    @Override
    public CustomObject visit(EEq eEq) throws Exception {

        Boolean res = false;

        CustomObject exp1 = eEq.exp_1.eval(this);

        CustomObject exp2 = eEq.exp_2.eval(this);

        if (exp1.type instanceof TypeString && exp2.type instanceof TypeString) {

            res = exp1.value.toString().equals(exp2.value.toString());

        } else {

            res = Double.parseDouble(exp1.value.toString()) == Double.parseDouble(exp2.value.toString());

        }

        return new CustomObject(new TypeBool(), res);
    }

    @Override
    public CustomObject visit(ENEq enEq) throws Exception {

        Boolean res = enEq.exp_1.eval(this).value != enEq.exp_2.eval(this).value;

        return new CustomObject(new TypeBool(), res);
    }

    @Override
    public CustomObject visit(EGt eGt) throws Exception {

        CustomObject exp1 = eGt.exp_1.eval(this);

        CustomObject exp2 = eGt.exp_2.eval(this);

        CustomObject res = null;

        if (!(exp1.type instanceof TypeBool || exp1.type instanceof TypeString) && !(exp2.type instanceof TypeBool || exp2.type instanceof TypeString)) {

            Boolean isGreater = Double.parseDouble(exp1.value.toString()) > Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeBool(), isGreater);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden comparar!");

        }

        return res;
    }

    @Override
    public CustomObject visit(EGtEq eGtEq) throws Exception {

        CustomObject exp1 = eGtEq.exp_1.eval(this);

        CustomObject exp2 = eGtEq.exp_2.eval(this);

        CustomObject res = null;

        if (!(exp1.type instanceof TypeBool || exp1.type instanceof TypeString) && !(exp2.type instanceof TypeBool || exp2.type instanceof TypeString)) {

            Boolean isGreater = Double.parseDouble(exp1.value.toString()) >= Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeBool(), isGreater);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden comparar!");

        }

        return res;

    }

    @Override
    public CustomObject visit(ELt eLt) throws Exception {

        CustomObject exp1 = eLt.exp_1.eval(this);

        CustomObject exp2 = eLt.exp_2.eval(this);

        CustomObject res = null;

        if (!(exp1.type instanceof TypeBool || exp1.type instanceof TypeString) && !(exp2.type instanceof TypeBool || exp2.type instanceof TypeString)) {

            Boolean isGreater = Double.parseDouble(exp1.value.toString()) < Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeBool(), isGreater);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden comparar!");

        }

        return res;
    }

    @Override
    public CustomObject visit(ELtEq eLtEq) throws Exception {

        CustomObject exp1 = eLtEq.exp_1.eval(this);

        CustomObject exp2 = eLtEq.exp_2.eval(this);

        CustomObject res = null;

        if (!(exp1.type instanceof TypeBool || exp1.type instanceof TypeString) && !(exp2.type instanceof TypeBool || exp2.type instanceof TypeString)) {

            Boolean isGreater = Double.parseDouble(exp1.value.toString()) <= Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeBool(), isGreater);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden comparar!");

        }

        return res;
    }

    @Override
    public CustomObject visit(EAnd eAnd) throws Exception {

        CustomObject exp1 = eAnd.exp_1.eval(this);
        CustomObject exp2 = eAnd.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeBool && exp2.type instanceof TypeBool) {

            Boolean resAnd = Boolean.parseBoolean(exp1.value.toString()) && Boolean.parseBoolean(exp2.value.toString());

            res = new CustomObject(new TypeBool(), resAnd);

        } else {

            throw new Exception("Type error: esta operación sólo se puede realizar con booleanos");

        }

        return res;
    }

    @Override
    public CustomObject visit(EOr eOr) throws Exception {

        CustomObject exp1 = eOr.exp_1.eval(this);
        CustomObject exp2 = eOr.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeBool && exp2.type instanceof TypeBool) {

            Boolean resAnd = Boolean.parseBoolean(exp1.value.toString()) || Boolean.parseBoolean(exp2.value.toString());

            res = new CustomObject(new TypeBool(), resAnd);

        } else {

            throw new Exception("Type error: esta operación sólo se puede realizar con booleanos");

        }

        return res;
    }

    @Override
    public CustomObject visit(EApp eApp) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(EAss eAss) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(EPlus ePlus) throws Exception {

        CustomObject exp1 = ePlus.exp_1.eval(this);
        CustomObject exp2 = ePlus.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeInt && exp2.type instanceof TypeInt) {

            Integer prod = Integer.parseInt(exp1.value.toString()) + Integer.parseInt(exp2.value.toString());

            res = new CustomObject(new TypeInt(), prod);

        } else if (exp1.type instanceof TypeDouble || exp2.type instanceof TypeDouble) {

            Double prod = Double.parseDouble(exp1.value.toString()) + Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeDouble(), prod);

        } else if (exp1.type instanceof TypeString || exp2.type instanceof TypeString) {

            String prod = exp1.value.toString() + exp2.value.toString();

            res = new CustomObject(new TypeString(), prod);

        } else {

            throw new Exception(exp1.type + " no se puede agregar a " + exp2.type);

        }

        return res;
    }

    @Override
    public CustomObject visit(EMinus eMinus) throws Exception {

        CustomObject exp1 = eMinus.exp_1.eval(this);
        CustomObject exp2 = eMinus.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeInt && exp2.type instanceof TypeInt) {

            Integer prod = Integer.parseInt(exp1.value.toString()) - Integer.parseInt(exp2.value.toString());

            res = new CustomObject(new TypeInt(), prod);

        } else if (exp1.type instanceof TypeDouble || exp2.type instanceof TypeDouble) {

            Double prod = Double.parseDouble(exp1.value.toString()) - Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeDouble(), prod);

        } else {

            throw new Exception("Type error: Sólo os datos numéricos se pueden restar!");

        }

        return res;
    }

    @Override
    public CustomObject visit(EDiv eDiv) throws Exception {

        CustomObject exp1 = eDiv.exp_1.eval(this);
        CustomObject exp2 = eDiv.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeInt && exp2.type instanceof TypeInt) {

            Integer prod = Integer.parseInt(exp1.value.toString()) / Integer.parseInt(exp2.value.toString());

            res = new CustomObject(new TypeInt(), prod);

        } else if (exp1.type instanceof TypeDouble || exp2.type instanceof TypeDouble) {

            Double prod = Double.parseDouble(exp1.value.toString()) / Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeDouble(), prod);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden dividir!");

        }

        return res;
    }

    @Override
    public CustomObject visit(ETimes eTimes) throws Exception {

        CustomObject exp1 = eTimes.exp_1.eval(this);
        CustomObject exp2 = eTimes.exp_2.eval(this);

        CustomObject res = null;

        if (exp1.type instanceof TypeInt && exp2.type instanceof TypeInt) {

            Integer prod = Integer.parseInt(exp1.value.toString()) * Integer.parseInt(exp2.value.toString());

            res = new CustomObject(new TypeInt(), prod);

        } else if (exp1.type instanceof TypeDouble || exp2.type instanceof TypeDouble) {

            Double prod = Double.parseDouble(exp1.value.toString()) * Double.parseDouble(exp2.value.toString());

            res = new CustomObject(new TypeBool(), prod);

        } else {

            throw new Exception("Type error: Sólo los datos numéricos se pueden multiplicar!");

        }

        return res;
    }

    @Override
    public CustomObject visit(TypeBool typeBool) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(TypeInt typeInt) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(TypeDouble typeDouble) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(TypeString typeString) throws Exception {
        return null;
    }

    @Override
    public CustomObject visit(TypeVoid typeVoid) throws Exception {
        return null;
    }
}
