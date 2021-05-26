package com.chuang.anarres.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.model.DomainFetchLog;
import com.chuang.tauceti.support.BiValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ath
 * @since 2021-02-10
 */
public interface DomainFetchLogMapper extends BaseMapper<DomainFetchLog> {

    @Update("<script> " +
            "UPDATE t_domain_fetch_log " +
            "SET times = CASE domain " +
            "<foreach collection=\"list\" item=\"o\">" +
            "   WHEN #{o.one} THEN times + #{o.two} " +
            "</foreach>" +
            "END " +
            "WHERE log_date=#{date} AND domain IN ( " +
            "<foreach collection=\"list\" item=\"o\" open=\"\" separator=\",\">" +
            "#{o.one}" +
            "</foreach>" +
            ") " +
            "</script>")
    void addTimes(@Param("list") List<BiValue<String, Integer>> list, @Param("date") String date);

    @Update("UPDATE t_domain_fetch_log " +
            "SET times = times + #{times} " +
            "WHERE log_date=#{date} AND domain=#{domain}")
    void addTime(@Param("domain") String domain, @Param("times") Integer times, @Param("date") String date);

    @Insert("insert ignore into `t_domain_fetch_log`" +
            "(domain, times, internal, log_date) " +
            "VALUES (#{domain}, 0, #{internal}, #{date})")
    void createIfAbsent(@Param("domain") String domain, Integer internal, @Param("date") String date);
}
