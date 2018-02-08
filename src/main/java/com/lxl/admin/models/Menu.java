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

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + this.getId() +
                ", url='" + this.getUrl() + '\'' +
                ", parentId=" + this.getParentId() +
                ", title='" + this.getTitle() + '\'' +
                ", path='" + this.getPath() + '\'' +
                ", seq=" + this.getSeq() +
                ", icon='" + this.getIcon() + '\'' +
                ", type='" + this.getType() + '\'' +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
