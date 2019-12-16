package assign6.parser ;

import assign6.visitor.* ;

public class DeclarationList extends Node {

    public DeclarationList left  =null;
    public DeclarationNode right =null;

    public DeclarationList () {

    }
    
    public DeclarationList (DeclarationList left, DeclarationNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
}
