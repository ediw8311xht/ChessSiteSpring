package com.csite.site.forms;


public class NewChessModel {
    private String id;

    NewChessModel(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String new_id) {
        this.id = new_id;
    }

}
