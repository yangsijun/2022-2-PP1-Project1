package com.mycom.word;

public interface ICRUD {
    public Object add();
    public Object update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
}
