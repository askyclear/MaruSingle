package com.hudini.korea.marusingle.constant;

/**
 * Created by korea on 2018-03-19.
 *
 */

public enum MaruTag {
    SEVENTEEN("17"),BL("BL"),SF("SF"),TS("TS"),COMADY("개그"),GAME("게임"),
    GAMBLE("도박"),DRAMA("드라마"),RANOVEL("라노벨"),LOVE("러브코미디"),
    COOKING("먹방"),GIRLLOVE("백합"),CRAZY("붕탁"),PURE("순정"),THRILLER("스릴러"),
    SPORT("스포츠"),ERA("시대"),ANIME("애니화"),ACTION("액션"),NEWWORLD("이세계"),
    GENERAL("일상%2B치유"),PASTLIFE("전생"),REASONING("추리"),FANTAGY("판타지"),
    ACADEMY("학원"),HORROR("호러"),DEFAULT("");
    private final String symbol;
    MaruTag(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return symbol;
    }
    public String getTypeQuery(){
        return "&genre="+symbol;
    }
}
