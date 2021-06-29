package MiniCScanner;

public enum TokenType {
    Const, Void, Else, If, Int, While, Return, 
    Char, String, Double, For, Do, Goto, Switch, Case, Break, Default, //추가한 키워드
    Eof, 
    LeftBrace, RightBrace, LeftBracket, RightBracket,
    LeftParen, RightParen, Semicolon, Comma, Assign, AddAssign, SubAssign, MultAssign, DivAssign, RemAssign,
    Equals, Less, LessEqual, Greater, GreaterEqual,
    Not, NotEqual, Plus, Minus, Multiply, Reminder,
    Increment, Decrement,Divide, And, Or, Colon, //추가한 연산자,주석
    Identifier, IntLiteral, CharLiteral, StringLiteral, DoubleLiteral, //char~double 추가한 리터럴
}
