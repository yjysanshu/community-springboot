package com.lxl.api.controllers;

import com.lxl.common.models.Advert;
import com.lxl.common.services.AdvertService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("apiAdvertController")
@RequestMapping("/api/advert")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/advert-list")
    public Map getAdvertList() {
        List<Advert> advertList = advertService.getAll();
        List<String> list = new ArrayList<>();
        for (Advert advert : advertList) {
            list.add(advert.getAdvertPic());
        }
        return FormatUtil.success(list);
    }
}
