package cn.xiangstudy.generalproject.utils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxiang
 * @date 2025-07-14 15:00
 */
public class ListUtils {

    /**
     * 判断列表是否为空
     * @author zhangxiang
     * @date 2025/7/14 15:06
     * @param collection
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 获取两个时间段之间没有的日期
     * @author zhangxiang
     * @date 2025/7/14 17:31
     * @param dateList
     * @param startDate
     * @param endDate
     * @return java.util.List<java.util.Date>
     */
    public static List<Date> findNoHaveDate(List<Date> dateList, Date startDate, Date endDate){
        List<Date> allDateList = DateUtils.startAndStopDate(startDate, endDate);

        return allDateList.stream().filter(single -> !dateList.contains(single))
                .collect(Collectors.toList());
    }

}
