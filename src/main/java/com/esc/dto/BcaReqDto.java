package com.esc.dto;

import com.esc.domain.Bca;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter // Controller 에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set메소드를 통해서만 값이 할당됨
@NoArgsConstructor
public class BcaReqDto {

    private String name;
    private int count;
    private String workday;

    public Bca toEntity(){

        workday = setWorkday(LocalDateTime.now());

        return Bca.builder().name(name).count(count).workday(workday).build();
    }

    private String setWorkday(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
