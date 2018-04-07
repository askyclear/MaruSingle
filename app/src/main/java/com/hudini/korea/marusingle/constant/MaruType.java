package com.hudini.korea.marusingle.constant;

/**
 * Created by korea on 2018-03-19.
 */

public enum MaruType {
    WEEKLY("주간"), MONTHLY("월간"), BIWEEKLY("격주"), OCCASIONAL("격월/비정기"),
    BOOK("단행본"), COMPLETE("완결"), SNIPPET("단편"), CRAZY("붕탁"),
    WHY("와이!"), AUTOCONOA("오토코노코 앤솔로지"), GENDER("여장소년 앤솔로지"), AUTOCONOT("오토코노코타임"),
    CRAZYEND("붕탁 완결"), EXTERNEL("");
    private final String symbol;
    MaruType(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return symbol;
    }
    public String getTypeQuery(){
        return "&cat="+symbol;
    }
}
