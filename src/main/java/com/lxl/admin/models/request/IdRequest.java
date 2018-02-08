package com.lxl.admin.models.request;

/**
 * 用于只接受id参数请求的数据
 */
public class IdRequest {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
