package assign6.parser ;

import assign6.visitor.* ;
import assign6.lexer.* ;

import java.io.* ;
import java.util.* ;


public class Env {

    private Hashtable table;
    protected Env prev;

    public Env(Env n) 
    {
	table = new Hashtable(); 
	prev = n;
    }

    public void put(String s, Token t) 
    {
	table.put(s,t); 
    }

    public Token get(String s) 
    {
        for( Env e = this; e != null; e = e.prev ) 
	{
    	    Token found = (Token)(e.table.get(s));

    	    if( found != null ) 
		return found;
        }
        return null;
    }
}
