package com.cailou.basicRegisterLoginAPI.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RoomTypeEnum {
    SOLTEIRO("Solteiro"),
    CASAL("Casal"),
    PREMIUM("Premium");

    private String name;

    @JsonCreator
    public static RoomTypeEnum decode(final String name) {
        return Stream.of(RoomTypeEnum.values()).filter(targetEnum -> targetEnum.name.equals(name))
                .findFirst().orElse(null);
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
