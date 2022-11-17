package com.newEng.greenpark.POJO.domain;

import com.newEng.greenpark.POJO.dto.vo.DomainVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanDomain extends DomainVo {
    private Byte value;
}
