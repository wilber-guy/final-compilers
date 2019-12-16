package assign6.parser ;

import assign6.visitor.* ;

public class StatementList extends Node {

    public StatementList left =null;
    public Statement right =null;

    public StatementList () {

    }
    
    public StatementList (StatementList left, Statement right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
