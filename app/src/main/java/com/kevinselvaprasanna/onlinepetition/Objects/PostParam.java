package com.kevinselvaprasanna.onlinepetition.Objects;

public class PostParam {

    String key;

    public PostParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
