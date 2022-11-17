package com.newEng.greenpark.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BooleanDomainMapper {
    Integer selectByHistoryId(String historyId);
    Integer countAllByHistoryId(String historyId);

    Integer deleteHalf(String historyId);
}
