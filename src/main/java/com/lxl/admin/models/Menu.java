package com.lxl.admin.models;

import java.util.List;

public class Menu extends MenuBase {
    private Boolean checked;
    private List<Menu> children;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
