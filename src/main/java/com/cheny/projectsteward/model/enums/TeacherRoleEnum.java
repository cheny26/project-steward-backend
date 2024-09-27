package com.cheny.projectsteward.model.enums;



public enum TeacherRoleEnum {

    Teacher("教师", 0),
    ADMIN("管理员", 1);


    private final String text;

    private final int value;

    TeacherRoleEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
