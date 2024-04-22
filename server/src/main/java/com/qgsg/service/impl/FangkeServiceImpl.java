package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.dto.FangkeDTO;
import com.qgsg.dto.FangkePageQueryDTO;
import com.qgsg.entity.Fangke;
import com.qgsg.mapper.FangkeMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.FangkeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FangkeServiceImpl implements FangkeService {

    @Autowired
    private FangkeMapper fangkeMapper;
    /**
     * 新增
     */
    @Override
    public void saveFangke(FangkeDTO fangkeDTO) {
        Fangke fangke = new Fangke();
        BeanUtils.copyProperties(fangkeDTO,fangke);
        fangke.setLocaldatetime(LocalDateTime.now());
        fangkeMapper.insert(fangke);
    }
    /**
     * 修改
     */
    @Override
    public void updateFangke(FangkeDTO fangkeDTO) {
        Fangke fangke = new Fangke();
        BeanUtils.copyProperties(fangkeDTO,fangke);
        fangkeMapper.update(fangke);
    }
    /**
     * 分页查
     */
    @Override
    public PageResult page(FangkePageQueryDTO fangkePageQueryDTO) {
        PageHelper.startPage(fangkePageQueryDTO.getPage(),fangkePageQueryDTO.getPageSize());
        Page<Fangke> fangke = fangkeMapper.pageQuery(fangkePageQueryDTO);
        return new PageResult(fangke.getTotal(), fangke.getResult());
    }
    /**
     * 根据查
     */
    @Override
    public Fangke getByName(String name) {
        Fangke fangke=fangkeMapper.getByName(name);
        return fangke;
    }
    /**
     * 批量删
     */
    @Override
    public void deleteFangke(List<String> names) {
        for(String name:names){
            fangkeMapper.deleteFangke(name);
        }
    }
}
