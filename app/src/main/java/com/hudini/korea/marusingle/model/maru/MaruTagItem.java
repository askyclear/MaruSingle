package com.hudini.korea.marusingle.model.maru;

import com.hudini.korea.marusingle.constant.MaruTag;

/**
 * Created by korea on 2018-04-02.
 */

public class MaruTagItem {
    private MaruTag tag;
    private boolean isChecked;

    public MaruTagItem(MaruTag tag, boolean isChecked) {
        this.tag = tag;
        this.isChecked = isChecked;
    }

    public MaruTag getTag() {
        return tag;
    }
    public boolean isChecked() {
        return isChecked;
    }
}
