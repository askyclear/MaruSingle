package com.hudini.korea.marusingle.core;

/**
 * Created by korea on 2018-03-26.
 */

public interface BaseAdapterPresenter<E>{
    void add(E e);
    E remove(int position);
    E getItem(int position);
    int getSize();
    void clear();

}
