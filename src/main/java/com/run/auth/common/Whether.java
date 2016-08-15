package com.run.auth.common;

/**
 * Created by dello on 2016/7/27.
 * 是否的枚举类型
 *
 */
public enum Whether {
    YES(1),
    NO(0);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    Whether(int value) {
        this.value = value;
    }
}
