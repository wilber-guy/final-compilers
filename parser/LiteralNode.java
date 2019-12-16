package assign6.parser ;

import assign6.visitor.* ;

public class LiteralNode extends Node {

    public String literal ;
    public int type ;
	public boolean initilized = false;

    public LiteralNode () {

    }
    
    public LiteralNode (String literal) {

        this.literal = literal ;
    }

    public void accept(ASTVisitor v) {

        v.visit(this);
    }

    public void setType(int i)
    {
        this.type = i;
    }
    public void setID(String s)
    {
        this.literal = s;
    }
    public String toString () {
        return literal;
    }


}
