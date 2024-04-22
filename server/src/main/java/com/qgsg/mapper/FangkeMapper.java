package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.FangkePageQueryDTO;
import com.qgsg.entity.Fangke;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FangkeMapper {

    /**
     * 插入
     */
    void insert(Fangke fangke);

    /**
     * 修改
     */
    void update(Fangke fangke);


    /**
     * 分页查询
     *
     * @param studentPageQueryDTO
     * @return
     */
    Page<Fangke> pageQuery(FangkePageQueryDTO studentPageQueryDTO);

    /**
     * 根据查
     *
     * @param number
     * @return
     */
    @Select("select * from fangke where name = #{name}")
    Fangke getByName(String number);

    /**
     * 根据删
     */
    @Delete("delete from fangke where name=#{name}")
    void deleteFangke(String number);

}
