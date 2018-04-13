package com.lxl.api.controllers;

import com.lxl.api.models.response.AdvResponse;
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
        List<Advert> advertList = advertService.getAdvertByType(0);
        List<AdvResponse> list = new ArrayList<>();
        AdvResponse advResponse;
        for (Advert advert : advertList) {
            advResponse = new AdvResponse();
            advResponse.setDepartment(advert.getAdvertDepartment());
            advResponse.setDes(advert.getAdvertContent());
            advResponse.setTitle(advert.getAdvertTitle());
            advResponse.setImg(advert.getAdvertPic());
            list.add(advResponse);
        }
        return FormatUtil.success(list);
    }

    @RequestMapping("/advert-home")
    public Map getAdvertHome() {
        List<Advert> advertList = advertService.getAdvertByType(1);
        List<String> list = new ArrayList<>();
        for (Advert advert : advertList) {
            list.add(advert.getAdvertPic());
        }
        return FormatUtil.success(list);
    }
}
