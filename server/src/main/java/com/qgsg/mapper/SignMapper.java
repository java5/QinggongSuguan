package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.dto.SignPageQueryDTO;
import com.qgsg.entity.Sign;
import com.qgsg.vo.SignVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SignMapper {

    /**
     * 更新签到信息
     * @param sign 包含更新信息的Sign对象
     */
    void update(Sign sign);

    /**
     * 根据学生页面查询条件获取签到信息分页
     * @param studentPageQueryDTO 包含查询条件的学生页面查询DTO
     * @return 返回SignVO对象的分页信息
     */
    Page<SignVO> signpage1(SignDTO studentPageQueryDTO);

    /**
     * 根据学号删除签到信息
     * @param number 学号
     */
    @Delete("delete from sign where number=#{number}")
    void deleteToSign1(String number);

    /**
     * 根据学号获取签到信息
     * @param number 学号
     * @return 返回SignVO对象的分页信息
     */
    @Select("select * from sign where number = #{number}")
    Page<SignVO> getByNumber(String number);

    /**
     * 根据宿舍号获取签到信息列表
     * @param dorNumber 宿舍号
     * @return 返回Sign对象列表
     */
    @Select("select * from sign where dormitory_number =#{dorNumber} ;")
    List<Sign> getByDornumer(String dorNumber);

    /**
     * 根据开始时间和结束时间计算签到总数
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 返回签到总数
     */
    Integer sumByMap(LocalDateTime beginTime,LocalDateTime endTime);

    /**
     * 根据学号获取签到信息列表
     * @param number 学号
     * @return 返回Sign对象列表
     */
    @Select("select * from sign where number = #{number}")
    List<Sign> getByNum(String number);

    /**
     * 根据开始时间和结束时间获取签到信息列表
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 返回SignVO对象列表
     */
    List<SignVO> select(LocalDateTime beginTime, LocalDateTime endTime);


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
}
