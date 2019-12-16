package assign6.intercode ;

import assign6.visitor.* ;
import assign6.lexer.* ;
import assign6.parser.*;

import java.io.* ;
import java.util.* ;

public class InterCode extends ASTVisitor 
{
	private Parser p;

	private int tempCount = 0;

	List<String> temps = new ArrayList<>();

	public InterCode(Parser parser)
	{
		p = parser;
		parser.bn.accept(this);
	}


    public void visit (BlockNode n)
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

    public void visit (Statement n){
		n.stmt.accept(this);
    }

    public void visit (AssignmentNode n){
		String var = n.left.literal;
		String tempAddress = null;		
		ArithmeticNode tempNode = n.right;
		LiteralNode tempLit = null;
		boolean arith = false;
		
		if(tempNode.right != null)
		{	
			arith = true;
			tempNode.accept(this);
		}
		

/*
		while(tempNode.right != null)
		{
			
			tempLit = tempNode.right.left;
			if(tempAddress == null)
			{
				System.out.println("t" + tempCount + ": " +tempNode.left + " " + tempNode.operator + " " + tempLit);

				tempAddress = "t" + tempCount;
				tempNode = tempNode.right;
				tempCount++;
				
			}
			else 
			{
				System.out.println("t" + tempCount + ": " +tempAddress + " " + tempNode.operator + " " + tempLit);

			tempAddress = "t" + tempCount;
			tempNode = tempNode.right;
			tempCount++;
			}
		}
*/
		if(arith)
		{
		System.out.println("t" + tempCount + ": " + var + " = " + " " + "t" + (tempCount - 1) );
		temps.add("t" + tempCount + ": " + var + " = " + " " + "t" + (tempCount - 1) );
		tempCount++;
		}
		else
		{
		System.out.println("t" + tempCount + ": " + var + " = " + " " + tempNode.left.literal );
		temps.add("t" + tempCount + ": " + var + " = " + " " + tempNode.left.literal );
		tempCount++;
		}
    }

    public void visit (LiteralNode n){

    }
    
    public void visit (DeclarationNode n){
    }




    public void visit (ArithmeticNode n)
	{
		System.out.println("*** Arith ***");
		if(n.right != null)	
		{

			ArithmeticNode tempNode = n.right;	
			if(tempNode.right != null)
			{
/*
				System.out.println("t" + tempCount + ": " + n.left.literal + " " + n.operator + " " + n.right.left);
				temps.add("t" + tempCount + ": " + n.left.literal + " " + n.operator + " " + n.right.left);
				tempCount++;		
*/
				tempNode.accept(this);	
			}
			System.out.println("*** HERE ***");

			System.out.println("t" + tempCount + ": " + tempNode.left.literal + " " + n.operator + " " + "t" + (tempCount - 1) );
		temps.add("t" + tempCount + ": " + tempNode.left.literal + " " + n.operator + " " + "t" + (tempCount - 1) );
		tempCount++;
		}
	

		

		
    }

    public void visit (IfNode n){
        if(n.right != null);
        n.right.accept(this);
    }
    
    public void visit (ForNode n){

        if(n.block != null);
        n.block.accept(this);
    }




}
