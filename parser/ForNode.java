package assign6.parser ;

import assign6.visitor.* ;

public class ForNode extends Statement {

    public AssignmentNode first =null;
    public ArithmeticNode second =null;
    public AssignmentNode third =null;
    public BlockNode block =null;

    public ForNode () {

    }
    
    public ForNode (AssignmentNode first, ArithmeticNode second, AssignmentNode third, BlockNode block) {

        this.first = first ;
        this.second = second ;
        this.third = third ;
        this.block = block ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }

}
