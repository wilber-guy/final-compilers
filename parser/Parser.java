package assign6.parser ;

import assign6.visitor.* ;
import assign6.lexer.* ;

import java.io.* ;
import java.util.* ;

public class Parser extends ASTVisitor {

    public BlockNode bn = null ;
    public Lexer lexer = null ;       
    private Token look ; 
    public Env top = null ; // current or top symbol table
    public LiteralNode[] declaredVariables = new LiteralNode[1000];
    int index = 0;
    public Parser (Lexer lexer) 
    { 
        this.lexer = lexer ;
	    look = readLexem();
        bn = new BlockNode() ;
        visit(bn) ;
    }
    
    public Parser () {

        bn = new BlockNode();
        visit(bn) ;
    }

    void match(int t) 
    {
        if(look.tag == t)
        {
            look = readLexem();
        }
        else error("syntax" + " look: " + look);

    }
    
    void error(String s) 
    { 
        throw new Error("near line "+lexer.line+": "+s); 
    }

    public void visit(BlockNode n) 
    {
        match('{');
		Env savedEnv = top;
		top = new Env(top);
        if(look.tag == Tag.BASIC){
            n.declarations = new DeclarationList();
            n.declarations.accept(this);
        }
        if(!look.toString().equals("}")){
            n.statements = new StatementList();
            n.statements.accept(this);
        }
        match('}');
		top = savedEnv;
    }


    public void visit (DeclarationList n) {
        n.right = new DeclarationNode() ;
        n.right.accept(this);
        
        if(look.tag == Tag.BASIC){
            n.left = new DeclarationList() ; 
            n.left.accept(this);  
        }
    }

    public void visit(DeclarationNode n)
    {
        int type;
        LiteralNode lit = new LiteralNode(look.toString());
        n.left = lit;
        n.left.setType(look.tag);
        switch(look.toString())
        {
            case "int":
                type = 269;
                break;
            case "float":
                type = 270;
                break;
			case "boolean":
				type = 272;
				break;
            default:
                type = 275;
                break;
        }
        look = readLexem();
        lit = new LiteralNode(look.toString());
        lit.setType(type);
        n.right = lit;
        declaredVariables[index] = lit;
        index++;
        look = readLexem();
        match(';');
    }

    public void visit(StatementList n)
    {     
        n.right = new Statement();
        n.right.accept(this);
        if(!look.toString().equals("}")){
            n.left = new StatementList();
            n.left.accept(this);
        }
    }

    public void visit(Statement n)
    {
        switch(look.tag)
        {
            case Tag.IF:
                n.stmt = new IfNode();
                n.stmt.accept(this);
                break;
            case Tag.FOR:
                n.stmt = new ForNode();
                n.stmt.accept(this);
                break;
            default: 
                if(!look.toString().equals("}"))
                {            
                    n.stmt = new AssignmentNode();
                    n.stmt.accept(this);
                }   
                break;
        }    
    }

    public void visit (AssignmentNode n) {
        boolean found = false;
        LiteralNode lit = new LiteralNode();
        for(int i = 0; i < index; i++)
        {
            if(declaredVariables[i].toString() == look.toString())
            {
                lit = declaredVariables[i];
                found = true;
            }
        }
        if(!found)
        {
            System.out.println("Variable not declared: " + look.toString());
            System.exit(0);
        }
        n.left = lit;
        look = readLexem();
        match('=');
        n.right = new ArithmeticNode() ;
        n.right.accept(this);
		// ADDED init
		n.left.initilized = true;
        match(';');
        
    }

    public void visit(ArithmeticNode n)
    {
        LiteralNode lit = new LiteralNode();
        if (look.tag != 264){
            lit.setID(look.toString());
            lit.setType(look.tag);
        }
        else
        {
            boolean found = false;
            for(int i = 0; i < index; i++)
            {
                if(declaredVariables[i].toString() == look.toString())
                {
                    lit = declaredVariables[i];
                    found = true;
					if(declaredVariables[i].initilized != true)
					{
						System.out.println("Variable not initilized: " + look.toString());
					}
                }
            }
            if(!found)
            {
                System.out.println("Variable not declared: " + look.toString());
                System.exit(0);
            }
        }
        n.left = lit;
        look = readLexem();
        
        boolean end = true;

        String[] higher = new String[]{"*", "/", "%"};
        String[] lower = new String[]{"+", "-"};
        String[] higherCompare = new String[]{">", "<", ">=", "<="};
        String[] lowerCompare = new String[]{"==", "!="};


        for(String higherComp: higherCompare)
        {   
            if(end && higherComp.equals(look.toString()))
            {
                end = false;
                n.operator = look.toString();
            }
        }
            
        for(String lowerComp: lowerCompare)
        {
                if(end && lowerComp.equals(look.toString()))
                {
                    end = false;
                    n.operator = look.toString();
                }
        }


        for(String higherElement: higher)
        {
            if(higherElement.equals(look.toString()))
            {
                end = false;
                n.operator = look.toString();
            }
        }
        
        for(String lowerElement: lower)
        {
            if(lowerElement.equals(look.toString()))
            {
                end = false;
                n.operator = look.toString();

            }
        }

        if (!end)
        {
            look = readLexem();
            n.right = new ArithmeticNode();
            n.right.accept(this);
        }

		
    }

    public void visit (IfNode n)
    {
        look = readLexem();
        match('(');
        n.left = new ArithmeticNode();
        n.left.accept(this);
        match(')');
        n.right = new BlockNode();
        n.right.accept(this);
    }

    public void visit (ForNode n)
    {
        look = readLexem();
        match('(');
        if(look.toString().equals(";"))
        {
            match(';');
            match(';');
        }
        else
        {
            n.first = new AssignmentNode();
            n.first.accept(this);
            n.second = new ArithmeticNode();
            n.second.accept(this);
            match(';');
            n.third = new AssignmentNode();
            n.third.accept(this);
            

        }
        match(')');
        n.block = new BlockNode();
        n.block.accept(this);
    }

    public void visit (LiteralNode n) {
	    n.literal = look.toString();
	    look = readLexem();
    }

    
    public Token readLexem () {

        Token t = null ;

        try {
            
            t = lexer.scan() ;
        }
        catch (IOException e) {

            System.out.println("IO Error") ;
        }
        return t ;
    }

}
