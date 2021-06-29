package MiniCScanner;

public enum TokenType {
    Const, Void, Else, If, Int, While, Return, 
    Char, String, Double, For, Do, Goto, Switch, Case, Break, Default, //�߰��� Ű����
    Eof, 
    LeftBrace, RightBrace, LeftBracket, RightBracket,
    LeftParen, RightParen, Semicolon, Comma, Assign, AddAssign, SubAssign, MultAssign, DivAssign, RemAssign,
    Equals, Less, LessEqual, Greater, GreaterEqual,
    Not, NotEqual, Plus, Minus, Multiply, Reminder,
    Increment, Decrement,Divide, And, Or, Colon, //�߰��� ������,�ּ�
    Identifier, IntLiteral, CharLiteral, StringLiteral, DoubleLiteral, //char~double �߰��� ���ͷ�
}
