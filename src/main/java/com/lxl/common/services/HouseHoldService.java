package com.lxl.common.services;

import com.lxl.admin.models.request.HouseHoldRequest;
import com.lxl.admin.models.response.HouseHoldResponse;
import com.lxl.common.mapper.HouseHoldMapper;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.models.HouseHold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HouseHoldService {

    @Autowired
    private HouseHoldMapper houseHoldMapper;

    public List<HouseHoldResponse> getList(HouseHoldRequest request) {
        HouseHold houseHoldSearch = formatModelDetail(request);
        List<HouseHold> listHouseHold = houseHoldMapper.selectAll(houseHoldSearch);
        List<HouseHoldResponse> list = new ArrayList<>();
        for (HouseHold houseHold : listHouseHold) {
            HouseHoldResponse houseHoldResponse = formatResponseDetail(houseHold);
            list.add(houseHoldResponse);
        }
        return list;
    }

    public Integer getTotal(HouseHoldRequest request) {
        HouseHold houseHoldSearch = formatModelDetail(request);
        return houseHoldMapper.getTotal(houseHoldSearch);
    }

    public Integer save(HouseHoldRequest request) {
        HouseHold houseHold;
        if (request.getId() != null) {
            houseHold = houseHoldMapper.selectByPrimaryKey(request.getId());
        } else {
            houseHold = new HouseHold();
            houseHold.setHouseHoldCreateAt(new Date());
        }
        houseHold.setHouseHoldRoomId(request.getRoomId());
        houseHold.setHouseHoldName(request.getName());
        houseHold.setHouseHoldPhone(request.getPhone());
        houseHold.setHouseHoldSex(request.getSex());
        houseHold.setHouseHoldIndividual(request.getIndividual());
        houseHold.setHouseHoldType(request.getType());
        houseHold.setHouseHoldDescription(request.getDescription());
        if (request.getId() != null) {
            return houseHoldMapper.updateByPrimaryKeySelective(houseHold);
        } else {
            return houseHoldMapper.insertSelective(houseHold);
        }
    }

    private HouseHoldResponse formatResponseDetail(HouseHold houseHold) {
        HouseHoldResponse response = new HouseHoldResponse();
        response.setId(houseHold.getHouseHoldId());
        response.setRoomId(houseHold.getHouseHoldRoomId());
        response.setName(houseHold.getHouseHoldName());
        response.setPhone(houseHold.getHouseHoldPhone());
        response.setSex(houseHold.getHouseHoldSex());
        response.setIndividual(houseHold.getHouseHoldIndividual());
        response.setType(houseHold.getHouseHoldType());
        response.setDescription(houseHold.getHouseHoldDescription());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(houseHold.getHouseHoldCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(houseHold.getHouseHoldUpdateAt()));
        return response;
    }

    private HouseHold formatModelDetail(HouseHoldRequest request) {
        HouseHold houseHold = new HouseHold();
        houseHold.setHouseHoldId(request.getId());
        houseHold.setHouseHoldRoomId(request.getRoomId());
        houseHold.setHouseHoldName(request.getName());
        houseHold.setHouseHoldPhone(request.getPhone());
        houseHold.setHouseHoldSex(request.getSex());
        houseHold.setHouseHoldIndividual(request.getIndividual());
        houseHold.setHouseHoldType(request.getType());
        houseHold.setHouseHoldDescription(request.getDescription());
        houseHold.setHouseHoldCreateAt(request.getCreateAt());
        houseHold.setHouseHoldUpdateAt(request.getUpdateAt());
        return houseHold;
    }
}
