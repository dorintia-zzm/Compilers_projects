package zzm.book.lexer;

/***
 * 类 Word 用于管理保留字，标识符和&&这样的复合词法单元的词素，它也可以用来管理在中间代码中运算符的
 * 书写形式；比如单目减号。
 */
public class Word extends Token{

    public String lexeme = "";
    public Word(String s,int tag) {
        super(tag);
        lexeme = s;
    }
    public String toString(){
        return lexeme;
    }
    public static final Word
        and = new Word("&&",Tag.AND), or = new Word("!!",Tag.OR),
        eq = new Word("==",Tag.EQ), ne = new Word("!=",Tag.NE),
        le = new Word("<=",Tag.LE), ge= new Word(">-",Tag.GE),
        minus= new Word("minus",Tag.MINUS),
        True= new Word("true",Tag.TRUE),
        False = new Word("false",Tag.FALSE),
        temp = new Word("t",Tag.TEMP);
}
