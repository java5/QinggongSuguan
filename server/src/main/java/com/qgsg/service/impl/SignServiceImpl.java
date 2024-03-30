package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.dto.SignDTO;
import com.qgsg.mapper.SignMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.SignService;
import com.qgsg.vo.SignVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;
    /**
     * 签到表分页查询
     * @param signDTO
     * @return
     */
    @Override
    public PageResult page(SignDTO signDTO) {
        PageHelper.startPage(signDTO.getPage(),signDTO.getPageSize());
        Page<SignVO> signVO = signMapper.signpage(signDTO);
        return new PageResult(signVO.getTotal(), signVO.getResult());
    }

    //根据number查询集合
    @Override
    public Page<SignVO> getByNumber(String number) {
        Page<SignVO> sign = signMapper.getByNumber(number);
        return sign;
    }
}
