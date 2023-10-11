package com.newEng.greenpark.pojo.domain;

import com.newEng.greenpark.pojo.model.vo.DomainVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NumberDomain extends DomainVo {
    private Double value;
}
