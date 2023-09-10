package com.jk.domain.student.enrollment;

public enum Grade {
	A_PLUS("A+"),
    A_ZERO("A0"),
    A_MIUS("A-"),
    B_PLUS("B+"),
    B_ZERO("B0"),
    B_MIUS("B-"),
    C_PLUS("C+"),
    C_ZERO("C0"),
    C_MIUS("C-"),
    D_PLUS("D+"),
    D_ZERO("D0"),
    D_MIUS("D-"),
    F("F");

    private final String value;

    Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
