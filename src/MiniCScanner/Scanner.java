package MiniCScanner;

import java.io.*;

public class Scanner {

    private boolean isEof = false;
    private char ch = ' '; 
    private BufferedReader input;
    private String line = "";
    static private int lineno = 0; //line number
    static private int col = 1; //column number
    private final String letters = "abcdefghijklmnopqrstuvwxyz"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789"; //10진수, 8진수 인식
    private final String hex_digits = "0123456789xXaAbBcCdDeEfF"; //16진수 인식
    private final char eolnCh = '\n';
    private final char eofCh = '\004';
    private String comment = "";
    

    public Scanner (String fileName) { // source filename
    	System.out.println("Begin scanning... programs/" + fileName + "\n");
        try {
            input = new BufferedReader (new FileReader(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
    }

    private char nextChar() { // Return next char
        if (ch == eofCh)
            error("Attempt to read past end of file");
        col++;
        if (col >= line.length()) {
            try {
                line = input.readLine( );
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } // try
            if (line == null) // at end of file
                line = "" + eofCh;
            else {
                // System.out.println(lineno + ":\t" + line);
                lineno++;
                line += eolnCh;
            } // if line
            col = 0;
        } // if col
        return line.charAt(col);
    }
            

    public Token next( ) { // Return next token
        do {
            if (isLetter(ch) || ch == '_') { // ident or keyword
                String spelling = concat(letters + digits + '_');
                return Token.keyword(spelling);
            } else if (isDigit(ch)|| ch =='.') { // int literal or double literal
            	if(ch == '.') { //double literal(.123) 
            		String number = concat(digits+'e'+'+'+'-'); //e가 있는경우
                	return Token.mkDoubleLiteral(number);
                }
            	String number = concat(digits + hex_digits);
                if(ch != '.') {
                	return Token.mkIntLiteral(number); // int literal
                }
                number += concat(digits+'e'+'+'+'-'); //e가 있는경우
                return Token.mkDoubleLiteral(number); //double literal(123. or 12.34) 
            } else switch (ch) {
            case ' ': case '\t': case '\r': case eolnCh:
                ch = nextChar();
                break;
            
            case '/':  // divide or divAssign or comment
                ch = nextChar();
                if (ch == '=')  { // divAssign
                	ch = nextChar();
                	return Token.divAssignTok;
                }
                
                // divide
                if (ch != '*' && ch != '/') return Token.divideTok;
                
                if (ch == '*') { 
                	if(nextChar()=='*') { // documented comment
                		do {
        					while (ch != '*' && ch!='\n') {
        						comment += ch;
        						ch = nextChar();
        					}ch = nextChar();
        				} while (ch != '/');
                		ch = nextChar();
                		System.out.println("Documented Comment----->"+comment);
                		comment="";
                		break;
                	}
                	else { // multi line comment
	    				do {
	    					while (ch != '*' && ch!='\n') {
	    						comment += ch;
	    						ch = nextChar();
	    					}ch = nextChar();
	    				} while (ch != '/');
	    				ch = nextChar();
	    				//System.out.println("Multi-line Comment----->"+comment);
	    				comment="";
	            		break;
                	}
                }
                else if (ch == '/')  {
                	if (nextChar()=='/') {
                		do {
    	                	comment += ch;
    	                    ch = nextChar();
    	                } while (ch != eolnCh);
    	                ch = nextChar();
    	                System.out.println("Single-line Documented Comment----->/"+comment);
    	                comment="";
                		break;
                	}
	                do {
	                	comment += ch;
	                    ch = nextChar();
	                } while (ch != eolnCh);
	                ch = nextChar();
	                //System.out.println("Single-line Comment----->/"+comment);
	                comment="";
            		break;
                }
                break;
            
            case '\'':  // char literal
                char ch1 = nextChar();
                nextChar(); // get '
                ch = nextChar();
                return Token.mkCharLiteral("'" + ch1+ "'");
                
            case '\"':  // string literal
                String str1 = "";
                do {				
                	ch = nextChar(); 
                	str1 += ch;
                } while(ch != '\"');
                ch= nextChar(); 
                return Token.mkStringLiteral('"' + str1);
                
            case eofCh: return Token.eofTok;
            
            case '+': 
            	ch = nextChar();
	            if (ch == '=')  { // addAssign
	            	ch = nextChar();
	            	return Token.addAssignTok;
	            }
	            else if (ch == '+')  { // increment
	            	ch = nextChar();
	            	return Token.incrementTok;
	            }
                return Token.plusTok;

            case '-': 
            	ch = nextChar();
                if (ch == '=')  { // subAssign
                	ch = nextChar();
                	return Token.subAssignTok;
                }
	            else if (ch == '-')  { // decrement
	            	ch = nextChar();
	            	return Token.decrementTok;
	            }
                return Token.minusTok;

            case '*': 
            	ch = nextChar();
                if (ch == '=')  { // multAssign
                	ch = nextChar();
                	return Token.multAssignTok;
                }
                return Token.multiplyTok;

            case '%': 
            	ch = nextChar();
                if (ch == '=')  { // remAssign
                	ch = nextChar();
                	return Token.remAssignTok;
                }
                return Token.reminderTok;

            case '(': ch = nextChar();
            return Token.leftParenTok;

            case ')': ch = nextChar();
            return Token.rightParenTok;

            case '{': ch = nextChar();
            return Token.leftBraceTok;

            case '}': ch = nextChar();
            return Token.rightBraceTok;
            
            case '[': ch = nextChar();
            return Token.leftBracketTok; //수정
            
            case ']': ch = nextChar();
            return Token.rightBracketTok; //수정

            case ';': ch = nextChar();
            return Token.semicolonTok;
            
            case ':': ch = nextChar();
            return Token.colonTok; //추가

            case ',': ch = nextChar();
            return Token.commaTok;
                
            case '&': check('&'); return Token.andTok;
            case '|': check('|'); return Token.orTok;

            case '=':
                return chkOpt('=', Token.assignTok,
                                   Token.eqeqTok);
            case '<':
                return chkOpt('=', Token.ltTok,
                                   Token.lteqTok);
            case '>': 
                return chkOpt('=', Token.gtTok,
                                   Token.gteqTok);
            case '!':
                return chkOpt('=', Token.notTok,
                                   Token.noteqTok);

            default:  error("Illegal character " + ch); 
            	ch = nextChar();
            } // switch
        } while (true);
    } // next


    private boolean isLetter(char c) {
        return (c>='a' && c<='z' || c>='A' && c<='Z');
    }
  
    private boolean isDigit(char c) {
        return (c>='0' && c<='9');
    }

    private void check(char c) {
        ch = nextChar();
        if (ch != c) 
            error("Illegal character, expecting " + c);
        ch = nextChar();
    }

    private Token chkOpt(char c, Token one, Token two) {
        ch = nextChar();
        if (ch != c)
            return one;
        ch = nextChar();
        return two;
    }

    private String concat(String set) {
        String r = "";
        do {
            r += ch;
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r;
    }

    public void error (String msg) {
        System.err.print(line);
        System.err.println("Error: column " + col + " " + msg);
        //System.exit(1);
    }

    static public void main ( String[] argv ) {
        Scanner lexer = new Scanner(argv[0]);
        System.out.println("Type  ----->      Token (Token Number,  token Value,    File Name, Line Number, Column Number");
        System.out.println("------------------------------------------------------------------------------------------------");
        Token tok = lexer.next( );
        while (tok != Token.eofTok) {
            System.out.println(tok.toString()+ String.format("   %10s, %11s, %12s )",argv[0],lineno,col));
            tok = lexer.next( );
        } 
    } // main
}
