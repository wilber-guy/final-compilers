package assign6.parser ;

import assign6.visitor.* ;
import assign6.lexer.* ;
import assign6.typechecker.*;
import assign6.intercode.* ;

public class ArithmeticNode extends Statement {

    public LiteralNode left  =null;
    public ArithmeticNode right =null;
    public String operator =null;

    public ArithmeticNode () {
        
    }
    public ArithmeticNode (LiteralNode left, ArithmeticNode right) {

        this.left  = left  ;
        this.right = right ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }

	public void accept(TypeChecker t) {
		t.visit(this);
	}

	public void accept(InterCode i) {
		i.visit(this);
	}

    public String output()
    {
        String output;
        if(right == null)
            return left.toString();
        else
            return output = left.toString()+operator+right.output();
    }

    public int checkType()
    {
	if(right == null)
	    return left.type;
	if(left.type != right.left.type)
	{	
		// ADDED allow for int and float opperations
		if((left.type == 270 && right.left.type == 269) || (left.type == 269 && right.left.type == 270))
		{	
			// return float
//			return 270;
 			return right.checkType();
		}

	    System.out.println("Type Mismatch " + left.type + ", " + right.left.type);
	    return right.checkType();
	}
	else
	    return right.checkType();
    }
}
