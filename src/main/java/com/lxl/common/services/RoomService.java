package com.lxl.common.services;

import com.lxl.admin.models.request.RoomRequest;
import com.lxl.admin.models.response.RoomResponse;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.mapper.RoomMapper;
import com.lxl.common.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private HouseService houseService;

    public List<RoomResponse> getAll() {
        List<Room> listRoom = roomMapper.findByParams(new Room());
        List<RoomResponse> list = new ArrayList<>();
        for (Room room : listRoom) {
            RoomResponse roomResponse = formatResponseDetail(room);
            roomResponse.setHouseName(houseService.getHouseName(roomResponse.getHouseId()));
            list.add(roomResponse);
        }
        return list;
    }

    /**
     * 获取显示的列表
     * @param request -
     * @return -
     */
    public List<RoomResponse> getList(RoomRequest request) {
        Map<String, Object> map = new HashMap<>();
        Room roomSearch = formatModelDetail(request);
        map.put("room", roomSearch);
        map.put("page", request.getCurrentPage());
        map.put("size", request.getLimit());
        List<Room> listRoom = roomMapper.findByParamsAndPage(map);
        List<RoomResponse> list = new ArrayList<>();
        for (Room room : listRoom) {
            RoomResponse roomResponse = formatResponseDetail(room);
            roomResponse.setHouseName(houseService.getHouseName(roomResponse.getHouseId()));
            list.add(roomResponse);
        }
        return list;
    }

    public Integer getTotal(RoomRequest request) {
        Room roomSearch = formatModelDetail(request);
        return roomMapper.findTotalByParams(roomSearch);
    }

    public String getRoomName(Integer roomId) {
        Room room = roomMapper.findOneById(roomId);
        return room.getRoomName();
    }

    public Integer save(RoomRequest request) {
        Room room;
        if (request.getId() != null) {
            room = roomMapper.findOneById(request.getId());
        } else {
            room = new Room();
            room.setRoomCreateAt(new Date());
        }
        room.setRoomHouseId(request.getHouseId());
        room.setRoomCode(request.getCode());
        room.setRoomName(request.getName());
        room.setRoomUnit(request.getUnit());
        room.setRoomFloor(request.getFloor());
        room.setRoomDescription(request.getDescription());
        if (request.getId() != null) {
            return roomMapper.updateByIdAndParams(room);
        } else {
            return roomMapper.insertByParams(room);
        }
    }

    public Integer delete(Integer id) {
        return roomMapper.deleteOneById(id);
    }

    private RoomResponse formatResponseDetail(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getRoomId());
        response.setHouseId(room.getRoomHouseId());
        response.setCode(room.getRoomCode());
        response.setName(room.getRoomName());
        response.setUnit(room.getRoomUnit());
        response.setFloor(room.getRoomFloor());
        response.setDescription(room.getRoomDescription());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(room.getRoomCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(room.getRoomUpdateAt()));
        return response;
    }

    private Room formatModelDetail(RoomRequest request) {
        Room room = new Room();
        room.setRoomId(request.getId());
        room.setRoomHouseId(request.getHouseId());
        room.setRoomCode(request.getCode());
        room.setRoomName(request.getName());
        room.setRoomUnit(request.getUnit());
        room.setRoomFloor(request.getFloor());
        room.setRoomDescription(request.getDescription());
        room.setRoomCreateAt(request.getCreateAt());
        room.setRoomUpdateAt(request.getUpdateAt());
        return room;
    }
}
