package assign6.typechecker ;

import assign6.visitor.* ;
import assign6.lexer.* ;
import assign6.parser.*;

import java.io.* ;
import java.util.* ;

public class TypeChecker extends ASTVisitor 
{

    private Parser p;

    public TypeChecker(Parser parser)	
    { 
	p = parser;
	parser.bn.accept(this);
    }

    public void visit(BlockNode n) 
    {
        if(n.declarations!=null)
	{
	    n.declarations.accept(this);
        }
	
	if(n.statements!=null)
	{
	    n.statements.accept(this);
	}
    }

    public void visit(StatementList n)
    {
        if(n.right != null)
        {    
            n.right.accept(this);
        }
        if(n.left != null)
        {
            n.left.accept(this);
        }
    }

    public void visit (DeclarationList n) 
    {
        if(n.right != null)
        {
            n.right.accept(this);
        }
        if(n.left != null)
        {
            n.left.accept(this);
        }
    }

    public void visit(Statement n)
    {
        n.stmt.accept(this);
    }

    public void visit (AssignmentNode n) 
    {
		int assignType = n.left.type;
        ArithmeticNode tempNode = n.right;
		int tempType = n.right.left.type;
		
		// allow for float = int
		if(assignType == 270)
		{
			while(tempNode.right != null)
			{
				tempType = tempNode.left.type;
				if(tempType != assignType && (tempType != 269))
				{
					System.out.println("Type Mismatch: " + printType(assignType) + ", " + printType(tempType));
				}
				tempNode = tempNode.right;
			}
			// check the last variable in assign
			tempType = tempNode.left.type;
			if(tempType != assignType && (tempType != 269))
			{
				System.out.println("Type Mismatch: " + printType(assignType) + ", " + printType(tempNode.left.type));
			}
			
		}

		else
		{
			while(tempNode.right != null)
			{
				tempType = tempNode.left.type;
				if(tempType != assignType)
				{
					System.out.println("Type Mismatch: " + printType(assignType) + ", " + printType(tempType));
				}
				tempNode = tempNode.right;
			}
			// check the last variable in assign
			tempType = tempNode.left.type;
			if(tempType != assignType)
			{
				System.out.println("Type Mismatch: " + printType(assignType) + ", " + printType(tempNode.left.type));
			}
		}		
    }

    public void visit(ArithmeticNode n)
    {

		

    }

    public void visit (LiteralNode n) 
    {
	    
    }

    public void visit(IfNode n)
    {
        if(n.right != null);
        n.right.accept(this);
    }

    public void visit(ForNode n)
    {

        if(n.block != null);
        n.block.accept(this);
    }

    public void visit (DeclarationNode n)
    {
		

    }

	public String printType(int type) {
		String myType;

		switch(type) {
			case 264:
				myType = "ID";
				break;
			case 269:
				myType = "INT";
				break;
			case 270:
				myType = "FLOAT";
				break;
			case 272:
				myType = "BOOLEAN";
				break;
			default:
				myType = "BASIC";
				break;
			}
		return myType;
	}

}


