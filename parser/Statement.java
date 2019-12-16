package assign6.parser ;

import assign6.visitor.* ;

public class Statement extends Node {

    public Statement stmt = null;
    public Statement () {

    }
    public Statement(Statement stmt)
    {
        this.stmt = stmt;
    }
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
