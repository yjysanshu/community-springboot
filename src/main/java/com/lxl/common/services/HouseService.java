package com.lxl.common.services;

import com.lxl.admin.models.request.HouseRequest;
import com.lxl.admin.models.response.HouseResponse;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.models.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;

    /**
     * 获取展示的列表
     * @param request -
     * @return -
     */
    public List<HouseResponse> getList(HouseRequest request) {
        Map<String, Object> map = new HashMap<>();
        House houseSearch = formatModelDetail(request);
        map.put("house", houseSearch);
        map.put("page", request.getCurrentPage());
        map.put("size", request.getLimit());
        List<House> listHouse = houseMapper.findByParamsAndPage(map);
        List<HouseResponse> list = new ArrayList<>();
        for (House house : listHouse) {
            HouseResponse response = formatInfoDetail(house);
            list.add(response);
        }
        return list;
    }

    /**
     * 获取所有的信息
     * @return -
     */
    public List<HouseResponse> getAll() {
        List<House> listHouse = houseMapper.findByParams(new House());
        List<HouseResponse> list = new ArrayList<>();
        for (House house : listHouse) {
            HouseResponse response = formatInfoDetail(house);
            list.add(response);
        }
        return list;
    }

    /**
     * 获取数据总数
     * @param request -
     * @return -
     */
    public Integer getTotal(HouseRequest request) {
        House houseSearch = formatModelDetail(request);
        return houseMapper.findTotalByParams(houseSearch);
    }

    public Integer save(HouseRequest request) {
        House house;
        if (request.getId() != null) {
            house = houseMapper.findOneById(request.getId());
        } else {
            house = new com.lxl.common.models.House();
            house.setHouseCreateAt(new Date());
        }
        house.setHouseCode(request.getCode());
        house.setHouseName(request.getName());
        house.setHouseDescription(request.getDescription());
        house.setHouseStages(request.getStages());
        if (request.getId() != null) {
            return houseMapper.updateByIdAndParams(house);
        } else {
            return houseMapper.insertByParams(house);
        }
    }

    public String getHouseName(Integer houseId) {
        House house = houseMapper.findOneById(houseId);
        return house.getHouseName();
    }

    public Integer delete(Integer id) {
        return houseMapper.deleteOneById(id);
    }

    private HouseResponse formatInfoDetail(House house) {
        HouseResponse response = new HouseResponse();
        response.setId(house.getHouseId());
        response.setCode(house.getHouseCode());
        response.setName(house.getHouseName());
        response.setStages(house.getHouseStages());
        response.setDescription(house.getHouseDescription());
        return response;
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
