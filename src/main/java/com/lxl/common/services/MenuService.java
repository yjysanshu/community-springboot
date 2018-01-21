package com.lxl.common.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lxl.admin.models.Menu;
import com.lxl.admin.models.MenuBase;
import com.lxl.common.mapper.AdminResourceMapper;
import com.lxl.common.models.AdminResource;
import com.lxl.common.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private AdminResourceMapper armapper;

    public Integer save(Menu menu) throws JsonProcessingException {
        AdminResource adminResource;
        if (menu.getId() != null) {
            adminResource = armapper.selectByPrimaryKey(menu.getId());
        } else {
            adminResource = new AdminResource();
            adminResource.setAdminResourceType(0);
        }
        adminResource.setAdminResourceParentId(menu.getParentId());
        adminResource.setAdminResourceTarget(menu.getUrl());
        adminResource.setAdminResourceData(JSONUtil.menuToJson(menu));
        if (menu.getId() != null) {
            return armapper.updateByPrimaryKeySelective(adminResource);
        } else {
            return armapper.insertSelective(adminResource);
        }
    }

    public List<MenuBase> getList(Menu request) throws IOException {
        List<AdminResource> listAdminResource = armapper.selectAll(formatModelDetail(request));
        List<MenuBase> listMenu = new ArrayList<>();
        for (AdminResource adminResource : listAdminResource) {
            MenuBase menu = formatInfoBaseDetail(adminResource);
            listMenu.add(menu);
        }

        return listMenu;
    }

    public List<MenuBase> getFatherList() throws IOException {
        List<AdminResource> listAdminResource = armapper.findByParentId(0);
        List<MenuBase> listMenu = new ArrayList<>();
        for (AdminResource adminResource : listAdminResource) {
            MenuBase menu = formatInfoBaseDetail(adminResource);
            listMenu.add(menu);
        }

        return listMenu;
    }

    public List<Menu> getUserMenu(Integer parentId) throws IOException {
        List<AdminResource> listAdminResource = armapper.findByParentId(parentId);
        List<Menu> listMenu = new ArrayList<>();
        for (AdminResource adminResource : listAdminResource) {
            Menu menu = formatInfoDetail(adminResource);
            menu.setChildren(getUserMenu(adminResource.getAdminResourceId()));
            listMenu.add(menu);
        }

        return listMenu;
    }

    private MenuBase formatInfoBaseDetail(AdminResource adminResource) throws IOException {
        Map map = JSONUtil.jsonToMap(adminResource.getAdminResourceData());
        MenuBase menu = new MenuBase();
        menu.setId(adminResource.getAdminResourceId());
        menu.setUrl(adminResource.getAdminResourceTarget());
        menu.setParentId(adminResource.getAdminResourceParentId());
        menu.setTitle((String) map.get("title"));
        menu.setPath((String) map.get("path"));
        menu.setSeq((Integer) map.get("seq"));
        menu.setIcon((String) map.get("icon"));
        menu.setType((String) map.get("type"));
        return menu;
    }

    private Menu formatInfoDetail(AdminResource adminResource) throws IOException {
        Map map = JSONUtil.jsonToMap(adminResource.getAdminResourceData());
        Menu menu = new Menu();
        menu.setId(adminResource.getAdminResourceId());
        menu.setUrl(adminResource.getAdminResourceTarget());
        menu.setParentId(adminResource.getAdminResourceParentId());
        menu.setTitle((String) map.get("title"));
        menu.setPath((String) map.get("path"));
        menu.setSeq((Integer) map.get("seq"));
        menu.setIcon((String) map.get("icon"));
        menu.setType((String) map.get("type"));
        menu.setChecked(true);
        return menu;
    }

    private AdminResource formatModelDetail(Menu menu) {
        AdminResource adminResource = new AdminResource();
        adminResource.setAdminResourceId(menu.getId());
        adminResource.setAdminResourceParentId(menu.getParentId());
        adminResource.setAdminResourceTarget(menu.getUrl());
        return adminResource;
    }
}
