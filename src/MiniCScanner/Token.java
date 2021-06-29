package MiniCScanner;

public class Token {
	
    private static final int KEYWORDS = TokenType.Eof.ordinal();

    private static final String[] reserved = new String[KEYWORDS];
    private static Token[] token = new Token[KEYWORDS];

    public static final Token notTok = new Token(TokenType.Not, "!",0);
    public static final Token noteqTok = new Token(TokenType.NotEqual, "!=",1);
    public static final Token reminderTok = new Token(TokenType.Reminder, "%",2);
    public static final Token remAssignTok = new Token(TokenType.RemAssign, "%=",3);
    public static final Token andTok = new Token(TokenType.And, "&&",6);
    public static final Token orTok = new Token(TokenType.Or, "||",7);
    public static final Token leftParenTok = new Token(TokenType.LeftParen, "(",8);
    public static final Token rightParenTok = new Token(TokenType.RightParen, ")",9);
    public static final Token multiplyTok = new Token(TokenType.Multiply, "*",10);
    public static final Token multAssignTok = new Token(TokenType.MultAssign, "*=",11);
    public static final Token plusTok = new Token(TokenType.Plus, "+",12);
    public static final Token incrementTok = new Token(TokenType.Increment, "++",13);
    public static final Token addAssignTok = new Token(TokenType.AddAssign, "+=",14);
    public static final Token commaTok = new Token(TokenType.Comma, ",",15);
    public static final Token minusTok = new Token(TokenType.Minus, "-",16);
    public static final Token decrementTok = new Token(TokenType.Decrement, "--",17);
    public static final Token subAssignTok = new Token(TokenType.SubAssign, "-=",18);
    public static final Token divideTok = new Token(TokenType.Divide, "/",19);
    public static final Token divAssignTok = new Token(TokenType.DivAssign, "/=",20);
    public static final Token semicolonTok = new Token(TokenType.Semicolon, ";",21);
    public static final Token colonTok = new Token(TokenType.Colon, ":",22); //추가한 연산자
    public static final Token ltTok = new Token(TokenType.Less, "<",23);
    public static final Token lteqTok = new Token(TokenType.LessEqual, "<=",24);
    public static final Token assignTok = new Token(TokenType.Assign, "=",25);
    public static final Token eqeqTok = new Token(TokenType.Equals, "==",26);
    public static final Token gtTok = new Token(TokenType.Greater, ">",27);
    public static final Token gteqTok = new Token(TokenType.GreaterEqual, ">=",28);
    public static final Token leftBraceTok = new Token(TokenType.LeftBrace, "{",29);
    public static final Token rightBraceTok = new Token(TokenType.RightBrace, "}",30);
    public static final Token leftBracketTok = new Token(TokenType.LeftBracket, "[",31);
    public static final Token rightBracketTok = new Token(TokenType.RightBracket, "]",32);
    public static final Token eofTok = new Token(TokenType.Eof, "<<EOF>>",33);
    public static final Token constTok = new Token(TokenType.Const, "const",34);
    public static final Token returnTok = new Token(TokenType.Return, "return",35);
    public static final Token voidTok = new Token(TokenType.Void, "void",36);
    public static final Token elseTok = new Token(TokenType.Else, "else",37);
    public static final Token ifTok = new Token(TokenType.If, "if",38);
    public static final Token intTok = new Token(TokenType.Int, "int",39);
    public static final Token whileTok = new Token(TokenType.While, "while",40);
    //추가한 Token
    public static final Token charTok = new Token(TokenType.Char, "char",41);
    public static final Token stringTok = new Token(TokenType.String, "string",42);
    public static final Token doubleTok = new Token(TokenType.Double, "double",43);
    public static final Token forTok = new Token(TokenType.For, "for",44);
    public static final Token doTok = new Token(TokenType.Do, "do",45);
    public static final Token gotoTok = new Token(TokenType.Goto, "goto",46);
    public static final Token switchTok = new Token(TokenType.Switch, "switch",47);
    public static final Token caseTok = new Token(TokenType.Case, "case",48);
    public static final Token breakTok = new Token(TokenType.Break, "break",49);
    public static final Token defaultTok = new Token(TokenType.Default, "default",50);
       

    private TokenType type;
    private String value = "";
    private int num;

    private Token (TokenType t, String v, int n) {
        type = t;
        value = v;
        num = n;
        if (t.compareTo(TokenType.Eof) < 0) { // keyword
            int ti = t.ordinal();
            reserved[ti] = v;
            token[ti] = this;
        }
    }

    public TokenType type( ) { return type; }

    public String value( ) { return value; }

    public static Token keyword  ( String name ) {
        char ch = name.charAt(0);
        if (ch >= 'A' && ch <= 'Z') // keyword(소문자) 아닌 경우
        	return mkIdentTok(name); //identifier 생성
        for (int i = 0; i < KEYWORDS; i++)
           if (name.equals(reserved[i]))  //keyword 인 경우
        	   return token[i]; //
        return mkIdentTok(name); //대문자인 identifier 생성
    } // keyword

    public static Token mkIdentTok (String name) {
        return new Token(TokenType.Identifier, name,4);
    }

    public static Token mkIntLiteral (String name) {
        return new Token(TokenType.IntLiteral, name,5);
    }

    public static Token mkCharLiteral (String name) {
        return new Token(TokenType.CharLiteral, name,51);
    }//charliteral 추가
    
    public static Token mkStringLiteral (String name) {
    	return new Token(TokenType.StringLiteral, name,52);
    }//stringliteral 추가
    
    public static Token mkDoubleLiteral (String name) {
    	return new Token(TokenType.DoubleLiteral, name,53);
    }//doubleliteral 추가
    
    public String toString ( ) {
        if (type.compareTo(TokenType.Identifier) < 0) {//literal,identifier 이 아니라면
        	return String.format("Token -----> %10s (%12d, %12s,",value,num,"0"); 
        }
        return String.format("Token -----> %10s (%12d, %12s,",value,num,value);
    } // toString

} // Token
