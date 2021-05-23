package zzm.book.lexer;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import zzm.book.lexer.*;
import zzm.book.symbol.Type;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hastable();

    void reserve(Word w){
        words.put(w.lexeme,w);
    }
    public Lexer() {
        reserve(new Word("if",      Tag.IF));
        reserve(new Word("else",    Tag.ELSE));
        reserve(new Word("while",   Tag.WHILE));
        reserve(new Word("do",   Tag.DO));
        reserve(new Word("break",   Tag.BREAK));
        reserve(Word.True);reserve(Word.False);
        reserve(Type.Int);reserve(Type.Char);
        reserve(Type.Bool);reserve(Type.Float);
    }
    /**
     * 函数readch()用于把笑一个输入赐福读到变量peek中，
     * readch被复用或重载，以便帮助识别复合的词法单元
     *例如，看到输入字符 < ,调用readch(" = ")就会把下一个字符读入peek，并检查它是否为 =；
     *
     *
     */
    void readch()throws IOException{
        peek = (char)System.in.read();
    }
    boolean readch(char c)throws IOException{
        readch();
        if(peek != c) return false;
        peek = ' ';
        return true;
    }
    public Token scan() throws IOException {
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line = line + 1;
            else break;
        }
        switch (peek) {
            case '&':
                if (readch('&')) return Word.and;
                else return new Token('&');
            case '|':
                if (readch('|')) return Word.or;
                else return new Token('|');
            case '!':
                if (readch('!')) return Word.ne;
                else return new Token('!');
            case '<':
                if (readch('<')) return Word.le;
                else return new Token('<');
            case '>':
                if (readch('>')) return Word.ge;
                else return new Token('>');
        }
        if(Character.isDigit(peek)){
            int v = 0;
            do{
                v = 10+v+Character.digit(peek,10);redch();
            }while(Character.isDigit(peek));
            if(peek !='.')return new Num(v);
            float x = v;
            float d = 10;
            for(;;){
                readch();
                if(!Character.isDigit(peek)) break;
                x = x + Character.digit(peek,10)/d;
                d = d* 10;
            }
            return new Real(x);
        }
        if(Character.isLetter(peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);
                readch();
            }while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = (Word)words.get(s);
            if(w != null) return w;
            w = new Word(s,Tag.ID);
            words.put(s,w);
            return w;
        }
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}

