package com.newEng.greenpark.utils;

import com.newEng.greenpark.POJO.dto.vo.PredictVo;
import com.newEng.greenpark.POJO.domain.SecondSmoothingEntity;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CalculateUtil {

    /**
     * 二次指数平滑公式：
     * St2 = a * S`t(一次平滑得到的预估值) + (1-a) * S2t-1 (上一次的二次平滑预估值)
     * At = 2 * St1 - St2
     * Bt = a / 1-a * (St1 - St2)
     * ^Yt+T (T为将来预测期数）= At + Bt * T
     */
    public static List<PredictVo> secondExponentialSmoothingMethod(SecondSmoothingEntity secondSmoothingEntity) {

        // （1）获取实际观察值列表和最后一次的预测值
        List<Double> realParamList = secondSmoothingEntity.getRealDataList();
        Double lastSinglePredictParam = secondSmoothingEntity.getLastSinglePredictParam();
        Double lastSecondPredictParam = secondSmoothingEntity.getLastSecondPredictParam();
        // 用于复位的
//        Double resizeSecondPredict = secondSmoothingEntity.getLastSecondPredictParam();
        int predictTime = secondSmoothingEntity.getPredictTime();
        // 定义结果集合类
        List<Double> singleGapList = new ArrayList<>();
        List<Double> secondGapList = new ArrayList<>();
        Map<Double, Double> singleGapMap = new HashMap<>();
        Map<Double, Double> secondGapMap = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double yt_T = lastSecondPredictParam;
        // （2）平滑值区间 [1~10]/10,先做一次平滑
        double a = 8.0;
        List<PredictVo> predictVoList = new ArrayList<>();
//        for (double a = 1; a < 10; a++) {
        for (Double realData : realParamList) {
            double smoothParam = a / 10;
//            System.out.println(smoothParam + ",实际值是：" + realData + ",一次平滑预测值是：" + lastSinglePredictParam + "，误差为：" + decimalFormat.format(Math.abs(realData - lastSinglePredictParam)) + "；二次平滑预测值是：" + yt_T + ",误差为：" + decimalFormat.format(Math.abs(realData - yt_T)));
            // 统计全部的误差
            singleGapList.add(Math.abs(realData - lastSinglePredictParam));
            secondGapList.add(Math.abs(realData - yt_T));

            double oldSecondPredictParam = Double.parseDouble(decimalFormat.format(yt_T));
            double secondError = Double.parseDouble(decimalFormat.format(Math.abs(realData - yt_T)));

            // 获得一次平滑预测值
            lastSinglePredictParam = smoothParam * realData + (1 - smoothParam) * lastSinglePredictParam;
            // 保留一位小数
            lastSinglePredictParam = Double.valueOf(decimalFormat.format(lastSinglePredictParam));
//            System.out.println("一次平滑预测下次为：" + lastSinglePredictParam);
            // 计算二次平滑值
            lastSecondPredictParam = smoothParam * lastSinglePredictParam + (1 - smoothParam) * lastSecondPredictParam;
            double at = 2 * lastSinglePredictParam - lastSecondPredictParam;
            double bt = smoothParam / (1 - smoothParam) * (lastSinglePredictParam - lastSecondPredictParam);
            // 计算出预测值
            yt_T = at + bt * predictTime;
//            System.out.println("二次平滑为：" + lastSecondPredictParam + ",下" + predictTime + "期的预测值为：" + yt_T);

            //装载返回体
            PredictVo predictVo = new PredictVo(a, oldSecondPredictParam, realData, yt_T, secondError);
            predictVoList.add(predictVo);
        }
        // 计算误差的平均值
        double totalSingleGap = 0.0;
        double totalSecondGap = 0.0;
        for (Double singleGap : singleGapList) {
            totalSingleGap = totalSingleGap + singleGap;
        }
        for (Double secondGap : secondGapList) {
            totalSecondGap = totalSecondGap + secondGap;
        }
        singleGapMap.put(a, totalSingleGap / (double) singleGapList.size());
        secondGapMap.put(a, totalSecondGap / (double) secondGapList.size());
//            // 每更换一个平滑值，预估值都要复位
//            lastSinglePredictParam = secondSmoothingEntity.getLastSinglePredictParam();
//            lastSecondPredictParam = secondSmoothingEntity.getLastSecondPredictParam();
//            yt_T = resizeSecondPredict;
//            // 清空当前list装的误差值
//            singleGapList.clear();
//            secondGapList.clear();
//        }
        log.info(singleGapMap + "----------------------------------" + secondGapMap);
        return predictVoList;
    }
}
