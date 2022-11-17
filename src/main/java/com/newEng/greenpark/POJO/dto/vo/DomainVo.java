package com.newEng.greenpark.POJO.dto.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomainVo {
    protected Integer id;
    protected String historyId;
    protected Long timeStamp;
    protected Object value;
}
