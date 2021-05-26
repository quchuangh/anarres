package com.chuang.anarres.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.model.SpiderLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface SpiderLogMapper extends BaseMapper<SpiderLog> {

    @Update("update t_spider_log set " +
            "baidu=baidu+#{baidu}, " +
            "haosou=haosou+#{haosou}, " +
            "sogou=sogou+#{sogou}, " +
            "yisou=yisou+#{yisou}, " +
            "other=other+#{other}, " +
            "updated_time=NOW() " +
            "where log_date=#{logDate}")
    boolean addSpider(@Param("baidu")Integer baidu,
                      @Param("haosou")Integer haosou,
                      @Param("sogou")Integer sogou,
                      @Param("yisou")Integer yisou,
                      @Param("other")Integer other,
                      @Param("logDate") LocalDate logDate);

    @Update("insert ignore into t_spider_log " +
            "(baidu, haosou, sogou, yisou, other, log_date, updated_time) " +
            "values " +
            "(#{o.baidu}, #{o.haosou}, #{o.sogou}, #{o.yisou}, #{o.other}, #{o.logDate}, #{o.updatedTime})")
    boolean saveOrIgnore(@Param("o") SpiderLog spiderLog);
}
