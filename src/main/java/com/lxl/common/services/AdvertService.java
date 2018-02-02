package com.lxl.common.services;

import com.lxl.admin.models.request.AdvertRequest;
import com.lxl.admin.models.response.AdvertResponse;
import com.lxl.common.mapper.AdvertMapper;
import com.lxl.common.models.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdvertService {

    @Autowired
    private AdvertMapper advertMapper;

    public List<AdvertResponse> getList(AdvertRequest request) {
        Advert advertSearch = formatModelDetail(request);
        List<Advert> listAdvert = advertMapper.findByParams(advertSearch);
        List<AdvertResponse> list = new ArrayList<>();
        for (Advert advert : listAdvert) {
            AdvertResponse advertResponse = formatResponseDetail(advert);
            list.add(advertResponse);
        }
        return list;
    }

    public Integer getTotal(AdvertRequest request) {
        Advert advertSearch = formatModelDetail(request);
        return advertMapper.findTotalByParams(advertSearch);
    }

    public Integer save(AdvertRequest request) {
        Advert advert;
        if (request.getId() != null) {
            advert = advertMapper.findOneById(request.getId());
        } else {
            advert = new Advert();
            advert.setAdvertCreateAt(new Date());
        }
        advert.setAdvertDepartment(request.getDepartment());
        advert.setAdvertTitle(request.getTitle());
        advert.setAdvertPic(request.getPic());
        advert.setAdvertType(request.getType());
        advert.setAdvertSort(request.getSort());
        advert.setAdvertIsTop(request.getIsTop());
        advert.setAdvertContent(request.getContent());
        if (request.getId() != null) {
            return advertMapper.updateByIdAndParams(advert);
        } else {
            return advertMapper.insertByParams(advert);
        }
    }

    public Integer delete(Integer id) {
        return advertMapper.deleteOneById(id);
    }

    private AdvertResponse formatResponseDetail(Advert advert) {
        AdvertResponse response = new AdvertResponse();
        response.setId(advert.getAdvertId());
        response.setTitle(advert.getAdvertTitle());
        response.setPic(advert.getAdvertPic());
        response.setType(advert.getAdvertType());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(advert.getAdvertCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(advert.getAdvertUpdateAt()));
        return response;
    }

    private Advert formatModelDetail(AdvertRequest request) {
        Advert advert = new Advert();
        advert.setAdvertId(request.getId());
        advert.setAdvertDepartment(request.getDepartment());
        advert.setAdvertTitle(request.getTitle());
        advert.setAdvertPic(request.getPic());
        advert.setAdvertType(request.getType());
        advert.setAdvertSort(request.getSort());
        advert.setAdvertIsTop(request.getIsTop());
        advert.setAdvertContent(request.getContent());
        advert.setAdvertCreateAt(request.getCreateAt());
        advert.setAdvertUpdateAt(request.getUpdateAt());
        return advert;
    }
}
