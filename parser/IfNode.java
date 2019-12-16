package assign6.parser ;

import assign6.visitor.* ;

public class IfNode extends Statement {

    public ArithmeticNode left =null;
    public BlockNode right = null;

    public IfNode () {

    }
    
    public IfNode (ArithmeticNode left, BlockNode right) {

        this.left = left ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }

}
