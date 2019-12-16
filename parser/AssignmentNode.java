package assign6.parser ;

import assign6.visitor.* ;
import assign6.lexer.* ;

public class AssignmentNode extends Statement {

    public LiteralNode left  =null;
    public ArithmeticNode right =null;

    public AssignmentNode () {
        
    }
    public AssignmentNode (LiteralNode left, ArithmeticNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }
    public String output()
    {
        String output;
        if(left.toString() == "")
            return "";
        return output = left.toString() + " = " + right.output();
    } 

	
}
