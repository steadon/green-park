package com.newEng.greenpark.mapper;

import com.newEng.greenpark.POJO.domain.NumberDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NumberDomainMapper {
    List<NumberDomain> selectByHistoryId(String historyId, long timeStamp);

    NumberDomain selectOne(String historyId);

    Double selectOneByHistoryId(String historyId, long timeStamp);

    Integer countAllByHistoryId(String historyId);

    Integer deleteHalf(String historyId);
}
