package com.lxl.admin.models.request;

import java.util.List;

public class UserRoleRequest {
    private Integer id;
    private List<Integer> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
