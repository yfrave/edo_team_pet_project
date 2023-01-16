package com.education.model.enumEntity;

public enum EnumResolution {

    Resolution("Резолюция"), DIRECTION("Направление"), REQUEST("Запрос");
    private String value;
    EnumResolution(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}

