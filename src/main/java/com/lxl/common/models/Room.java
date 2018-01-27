package com.lxl.common.models;

import java.util.Date;

public class Room {
    private Integer roomId;
    private Integer roomHouseId;
    private String roomCode;
    private String roomName;
    private Integer roomUnit;
    private Integer roomFloor;
    private String roomDescription;
    private Date roomCreateAt;
    private Date roomUpdateAt;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomHouseId() {
        return roomHouseId;
    }

    public void setRoomHouseId(Integer roomHouseId) {
        this.roomHouseId = roomHouseId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomUnit() {
        return roomUnit;
    }

    public void setRoomUnit(Integer roomUnit) {
        this.roomUnit = roomUnit;
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Date getRoomCreateAt() {
        return roomCreateAt;
    }

    public void setRoomCreateAt(Date roomCreateAt) {
        this.roomCreateAt = roomCreateAt;
    }

    public Date getRoomUpdateAt() {
        return roomUpdateAt;
    }

    public void setRoomUpdateAt(Date roomUpdateAt) {
        this.roomUpdateAt = roomUpdateAt;
    }
}
