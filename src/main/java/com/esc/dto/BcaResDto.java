package com.esc.dto;


import com.esc.domain.Bca;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class BcaResDto {
    private Long id;
    private String workday;
    private String name;
    private int count;
    private String regDate;

    public BcaResDto(Bca entity){
        id = entity.getId();
        workday = entity.getWorkday();
        name = entity.getName();
        count = entity.getCount();
        regDate = toStringDateTime(entity.getRegDate());
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
