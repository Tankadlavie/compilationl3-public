import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;


    @Override
    public void caseAOuExp(AOuExp node) {
        super.caseAOuExp(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1,op2);
    }

    @Override
    public void caseAEtExp1(AEtExp1 node) {
        super.caseAEtExp1(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1,op2);
    }

    @Override
    public void caseAEgalExp2(AEgalExp2 node) {
        super.caseAEgalExp2(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1,op2);
    }

    @Override
    public void caseAInfExp2(AInfExp2 node) {
        super.caseAInfExp2(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1,op2);
    }

    @Override
    public void caseAPlusExp3(APlusExp3 node) {
        super.caseAPlusExp3(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1,op2);
    }

    @Override
    public void caseAMoinsExp3(AMoinsExp3 node) {
        super.caseAMoinsExp3(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1,op2);
    }

    @Override
    public void caseAFoisExp4(AFoisExp4 node) {
        super.caseAFoisExp4(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1,op2);
    }

    @Override
    public void caseADiviseExp4(ADiviseExp4 node) {
        super.caseADiviseExp4(node);
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1,op2);
    }

    @Override
    public void caseANonExp5(ANonExp5 node) {
        super.caseANonExp5(node);
        SaExp op1 = null;
        node.getExp5().apply(this);
        op1 = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op1);
    }

    @Override
    public void caseALireExp6(ALireExp6 node) {
        super.caseALireExp6(node);
        this.returnValue = new SaExpLire();
    }

    @Override
    public void caseAAppelfctExp6(AAppelfctExp6 node) {
        super.caseAAppelfctExp6(node);
        SaAppel op1=null;
        this.returnValue = new SaExpAppel(op1);
    }

    @Override
    public void caseAParenthesesExp6(AParenthesesExp6 node) {
        super.caseAParenthesesExp6(node);
        SaExp op1 = null;
        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;
        this.returnValue = op1;
    }

    @Override
    public void caseAVarExp6(AVarExp6 node) {
        super.caseAVarExp6(node);
        this.returnValue = null;
    }

    @Override
    public void caseANombreExp6(ANombreExp6 node) {
        super.caseANombreExp6(node);
        int op1 = 0;
        node.getNombre().apply(this);
        op1 =  Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaExpInt(op1);
    }


    @Override
    public void caseALdecfoncProgramme(ALdecfoncProgramme node) {
        super.caseALdecfoncProgramme(node);
        SaDecFonc op1 = null;
        node.getListedecfonc().apply(this);
        op1 = (SaDecFonc) this.returnValue;
        this.returnValue = op1;
    }


    public SaNode getRoot() {

        return returnValue;
    }

}

