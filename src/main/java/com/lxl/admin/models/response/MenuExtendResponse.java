package com.lxl.admin.models.response;

import java.util.List;

public class MenuExtendResponse extends MenuResponse {
    private Boolean checked;
    private List<MenuExtendResponse> children;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<MenuExtendResponse> getChildren() {
        return children;
    }

    public void setChildren(List<MenuExtendResponse> children) {
        this.children = children;
    }
}
