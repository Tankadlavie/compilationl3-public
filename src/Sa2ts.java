import sa.*;
import ts.*;
import sc.analysis.*;
import sc.node.*;



public class Sa2ts extends SaDepthFirstVisitor <Void>{
    Ts Table_De_Symbole_Globale;
    Ts Table_locale = null;

    public Sa2ts(SaNode SaRoot)
    {
        Table_De_Symbole_Globale = new Ts();
        Table_locale = Table_De_Symbole_Globale;
    }

    public boolean existeVAR(String identif)
    {
        boolean existed=false;
        if (Table_locale.variables.containsKey(identif))
            existed=true;
        return existed;
    }

    public boolean existeVARglob(String identif)
    {
        boolean existed=false;
        if (Table_De_Symbole_Globale.variables.containsKey(identif))
            existed=true;
        return existed;
    }


    public boolean existeFONC(String identif)
    {
        boolean existed=false;
        if (Table_De_Symbole_Globale.fonctions.containsKey(identif))
            existed=true;
        return existed;
    }

    @Override
    public Void visit(SaDecVar node) {
        if (existeVAR(node.tsItem.identif))
            System.exit(1);
        if (node.tsItem.isParam)
            Table_locale.addParam(node.tsItem.identif);
        else
            Table_locale.addVar(node.tsItem.identif,node.tsItem.getTaille());

        return null;
    }

    @Override
    public Void visit(SaDecTab node) {
        if (Table_locale == Table_De_Symbole_Globale)
            System.exit(1);

        if (existeVAR(node.tsItem.identif))
            System.exit(1);

        Table_locale.addVar(node.tsItem.identif,node.tsItem.getTaille());


        return null;
    }

    @Override
    public Void visit(SaDecFonc node) {
        if (Table_locale!=Table_De_Symbole_Globale)
            System.exit(1);

        if (existeFONC(node.tsItem.identif))
            System.exit(1);

        Table_locale = new Ts();
        Table_De_Symbole_Globale.addFct(node.tsItem.identif,node.tsItem.nbArgs,Table_locale,node);

        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        if (!existeVAR(node.tsItem.identif))
            System.exit(1);
        if (!existeVARglob(node.tsItem.identif))
            System.exit(1);

        return null;
    }

    public Ts getTableGlobale()
    {
        return Table_De_Symbole_Globale;
    }
}
