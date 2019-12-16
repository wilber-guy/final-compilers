package assign6.parser ;

import assign6.visitor.* ;

public class BlockNode extends Node {

    //Node ast ;
    public StatementList statements=null;
    public DeclarationList declarations=null;

    public BlockNode () {

    }

    public BlockNode (StatementList statements, DeclarationList declarations)
    {
        this.statements = statements;
        this.declarations = declarations;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
