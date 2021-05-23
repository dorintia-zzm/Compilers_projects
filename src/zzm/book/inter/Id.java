package zzm.book.inter;

import zzm.book.lexer.Word;
import zzm.book.symbol.Type;

public class Id extends Expr{
    public int offset;
    public Id(Word id, Type p, int b){
        super(id,p);
        offset = b;
    }
}
