package com.example.kolin.minesweeper4.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by kolin on 31.10.2016.
 */

public abstract class AbstractPresenter<T> {

    private WeakReference<T> weakReference = null;

    public void attacheView(T view){
        weakReference = new WeakReference<>(view);
    }

    public void detacheView(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    public boolean isViewAttache(){
        return weakReference.get() != null && weakReference != null;
    }

    public T getWeakreference(){
        return weakReference == null ? null : weakReference.get();
    }

}
