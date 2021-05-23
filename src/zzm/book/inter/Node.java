package zzm.book.inter;

import zzm.book.lexer.Lexer;

public class Node {
    int lexline = 0;
    Node(){
        lexline = Lexer.line;
    }
    void error(String s){
        throw new Error("near line" + lexline +": " + s);
    }
    static int lablels = 0;
    public int newlablel(){
        return ++lablels;
    }
    public void emitlabel(int i){
        System.out.println("L" + i + ":");
    }
    public void emit(String s){
        System.out.println("\t" + s);
    }
}
