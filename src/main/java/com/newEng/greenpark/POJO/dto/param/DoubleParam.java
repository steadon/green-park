package com.newEng.greenpark.POJO.dto.param;

import com.newEng.greenpark.POJO.domain.NumberDomain;
import lombok.Data;

@Data
public class DoubleParam {
    private Integer id;
    private String historyId;
    private String timeStamp;
    private Double value;

    public DoubleParam(NumberDomain domain) {
        this.id = domain.getId();
        this.historyId = domain.getHistoryId();
        this.value = domain.getValue();
        this.timeStamp = String.valueOf(domain.getTimeStamp());
    }
}
