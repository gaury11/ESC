package com.esc.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ESC_BCA")
@Getter
@NoArgsConstructor
public class Bca extends BaseTimeEntity {

    /**
     * CREATE TABLE `ESC_BCA` (
     *   `ID` bigint(20) NOT NULL AUTO_INCREMENT,
     *   `WORKDAY` varchar(8) DEFAULT NULL,
     *   `NAME` varchar(10) NOT NULL,
     *   `PAY` int(10) DEFAULT NULL,
     *   `COUNT` int(10) DEFAULT NULL,
     *   `REG_DATE` datetime NOT NULL,
     *   PRIMARY KEY (`ID`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
     * ;
     */

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "WORKDAY")
    private String workday;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PAY")
    private int pay;

    @Column(name = "COUNT")
    private int count;

    @Override
    public String toString() {
        return "Bca{" +
                "id=" + id +
                ", workday='" + workday + '\'' +
                ", name='" + name + '\'' +
                ", pay=" + pay +
                ", count=" + count +
                ", regDate=" + super.getRegDate() +
                ", uptDate=" + super.getUptDate() +
                '}';
    }

    @Builder
    public Bca(String workday, String name, int pay, int count){
        this.workday = workday;
        this.name = name;
        this.pay = pay;
        this.count = count;
    }
}
