package assign6.unparser ;

import assign6.visitor.* ;
import assign6.lexer.* ;
import assign6.parser.*;

import java.io.* ;
import java.util.* ;

public class Unparser extends ASTVisitor {
    
    int indentCount;
    public Parser p;
    public String output = "";

    public Unparser(Parser parser)
    {
        parser.bn.accept(this);
        try{
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
            pw.print(output);
            pw.close();
        }catch(IOException e){}
    }

    public void visit(BlockNode n) 
    {
        output = output + indent()+'{' +"\n";
        indentCount++;
        if(n.declarations!=null){
            n.declarations.accept(this);
        }
        if(n.statements!=null){
            n.statements.accept(this);
        }
        indentCount--;
        output = output + indent()+'}' +"\n";

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

    public void visit(DeclarationNode n)
    {
        String indent = indent();
        output = output + indent + n.output() + " ;" +"\n";
    }


    public void visit (AssignmentNode n) {
        String indent = indent();
        output = output + indent + n.output() + " ;" +"\n";
    }

    public void visit(ArithmeticNode n)
    {

    }

    public void visit (LiteralNode n) 
    {
	    
    }

    public void visit(IfNode n)
    {
        output = output + indent() + "if("+n.left.output()+")" +"\n";
        if(n.right != null);
            n.right.accept(this);
    }

    public void visit(ForNode n)
    {
        if(n.first == null){
            output = output + indent()+"for( ; ; )" +"\n";
        }
        else{
            output = output + indent() + "for("+n.first.output()+"; "+n.second.output()+"; "+n.third.output()+")" +"\n";
        }
        if(n.block != null);
        n.block.accept(this);
    }

    public String indent(){
        String indent = "";
        for(int i =0; i<indentCount; i++)
        {
            indent = indent + "    ";
        }
        return indent;
    }
    
}
