package assign6.parser ;

import assign6.visitor.* ;
import assign6.lexer.* ;
import assign6.typechecker.* ;

public class DeclarationNode extends Node {

    public LiteralNode left  =null;
    public LiteralNode right =null;

    public DeclarationNode () {

    }
    
    public DeclarationNode (LiteralNode left, LiteralNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
	public void accept(TypeChecker t) {
		t.visit(this);
	}

    public String output()
    {
        String output;
        return output = left.toString() + ' ' + right.toString();
    }
}

