package com.education.model.enumEntity;

public enum EnumEmployment {

    UNEMPLOYED("Безработный"), WORKER("Работник"), STUDENT("Учащийся");
    private String value;

    EnumEmployment(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
