package com.example.netlibrary.bean.investment.entity;

/**
 * 收款公司
 *
 * @version 1.0
 * @date 2019-11-27 15:08
 */
public enum ReceiptCompanyEnum {
    xiaomonv("小魔女科技有限公司"),
    jmjby("家居装饰有限公司"),
    soalt("赛欧艾立特有限公司");

    private String value;

    private ReceiptCompanyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
