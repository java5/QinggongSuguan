package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.dto.SignPageQueryDTO;
import com.qgsg.entity.Sign;
import com.qgsg.entity.Student;
import com.qgsg.vo.SignVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SignMapper {
    /**
     * 修改签到表
     * @param sign
     * @return
     */
    void update(Sign sign);
    /**
     * 签到表分页查询
     * @param signPageQueryDTO
     * @return
     */
    Page<SignVO> signpage(SignPageQueryDTO signPageQueryDTO);
    /**
     * 根据id删除签到表
     * @param id
     * @return
     */
    @Delete("delete from sign where id=#{id}")
    void deleteToSign(int id);

//    @Select("select * from sign where number = #{number}")
//    Page<SignVO> getByNumber(String number);

    /**
     * 查询所有签到信息
     * @return
     */
    @Select("select * from sign")
    List<Sign> selectAll();

    /**
     * 查询今日的签到/未签人数
     * @param begin
     * @param end
     * @param signStatus
     * @return
     */
    Integer selectToday(LocalDateTime begin, LocalDateTime end,Object signStatus);
}
