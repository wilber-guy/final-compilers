package assign6.visitor ;

import assign6.parser.* ;

public class ASTVisitor {

    public void visit (BlockNode n){
        n.accept(this);
    }

    public void visit (AssignmentNode n){
        n.accept(this);
    }

    public void visit (LiteralNode n){
        n.accept(this);
    }
    
    public void visit (DeclarationNode n){
        n.accept(this);
    }

    public void visit (DeclarationList n){
        n.accept(this);
    }


    public void visit (ArithmeticNode n){
        n.accept(this);
    }

    public void visit (IfNode n){
        n.accept(this);
    }
    
    public void visit (ForNode n){
        n.accept(this);
    }

    public void visit (StatementList n){
        n.accept(this);
    }

    public void visit (Statement n){
        n.accept(this);
    }
}
