package com.lxl.common.services;

import com.lxl.admin.models.HouseRequest;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.models.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;

    public List<HouseRequest> getList(HouseRequest request) {
        House houseSearch = formatModelDetail(request);
        List<House> listHouse = houseMapper.selectAll(houseSearch);
        List<HouseRequest> list = new ArrayList<>();
        for (House house : listHouse) {
            HouseRequest houseRequest = formatInfoDetail(house);
            list.add(houseRequest);
        }
        return list;
    }

    public Integer getTotal(HouseRequest request) {
        House houseSearch = formatModelDetail(request);
        return houseMapper.getTotal(houseSearch);
    }

    public Integer save(HouseRequest request) {
        House house;
        if (request.getId() != null) {
            house = houseMapper.selectByPrimaryKey(request.getId());
        } else {
            house = new House();
            house.setHouseCreateAt(new Date());
        }
        house.setHouseCode(request.getCode());
        house.setHouseName(request.getName());
        house.setHouseDescription(request.getDescription());
        house.setHouseStages(request.getStages());
        if (request.getId() != null) {
            return houseMapper.updateByPrimaryKeySelective(house);
        } else {
            return houseMapper.insertSelective(house);
        }
    }

    private HouseRequest formatInfoDetail(House house) {
        HouseRequest houseRequest = new HouseRequest();
        houseRequest.setId(house.getHouseId());
        houseRequest.setCode(house.getHouseCode());
        houseRequest.setName(house.getHouseName());
        houseRequest.setStages(house.getHouseStages());
        houseRequest.setDescription(house.getHouseDescription());
        return houseRequest;
    }

    private House formatModelDetail(HouseRequest request) {
        House house = new House();
        house.setHouseId(request.getId());
        house.setHouseCode(request.getCode());
        house.setHouseName(request.getName());
        house.setHouseStages(request.getStages());
        house.setHouseDescription(request.getDescription());
        house.setHouseCreateAt(request.getCreateAt());
        house.setHouseUpdateAt(request.getUpdateAt());
        return house;
    }
}
