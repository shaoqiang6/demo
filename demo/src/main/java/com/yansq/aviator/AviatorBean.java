package com.yansq.aviator;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yanshaoqiang
 * @date 2019/1/21 17:11
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AviatorBean {
    private int id;
    private BigDecimal prize;
    private Date date;
}
