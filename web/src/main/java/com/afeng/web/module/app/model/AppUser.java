package com.afeng.web.module.app.model;

import java.io.Serializable;

public class AppUser implements Serializable {
    private static final long serialVersionUID = -1407825861090680571L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
