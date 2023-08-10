package com.navi.Assignment3.Response;

public class ResponseSource {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResponseSource(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
