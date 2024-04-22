package com.qgsg.service;

import com.qgsg.dto.FangkeDTO;
import com.qgsg.dto.FangkePageQueryDTO;
import com.qgsg.entity.Fangke;
import com.qgsg.result.PageResult;

import java.util.List;

public interface FangkeService {
    /**
     * 新增
     */
    void saveFangke(FangkeDTO FangkeDTO);
    /**
     * 修改
     */
    void updateFangke(FangkeDTO FangkeDTO);
    /**
     * 分页
     */
    PageResult page(FangkePageQueryDTO FangkePageQueryDTO);
    /**
     * 根据查询
     */
    Fangke getByName(String name);
    /**
     * 删除
     */
    void deleteFangke(List<String> names);

}
