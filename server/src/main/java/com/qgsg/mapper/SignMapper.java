package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.entity.Sign;
import com.qgsg.vo.SignVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SignMapper {

    void update(Sign sign);

    Page<SignVO> signpage(SignDTO studentPageQueryDTO);
    @Delete("delete from sign where number=#{number}")
    void deleteToSign(String number);

    @Select("select * from sign where number = #{number}")
    Page<SignVO> getByNumber(String number);

    @Select("select * from sign where dormitory_number =#{dorNumber} ;")
    List<Sign> getByDornumer(String dorNumber);

    Integer sumByMap(LocalDateTime beginTime,LocalDateTime endTime);

    @Select("select * from sign where number = #{number}")
    List<Sign> getByNum(String number);

    List<SignVO> select(LocalDateTime beginTime, LocalDateTime endTime);
}
