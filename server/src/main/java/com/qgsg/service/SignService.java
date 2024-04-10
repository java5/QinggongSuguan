package com.qgsg.service;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.entity.Sign;
import com.qgsg.result.PageResult;
import com.qgsg.vo.SignVO;

import java.util.List;

public interface SignService {

    /**
     * 签到表分页查询
     * @param signDTO
     * @return
     */
    PageResult page(SignDTO signDTO);


    /**
     * 根据学号查签到表
     * @param s
     * @return
     */
    Page<SignVO> getByNumber(String s);

    void deleteSign(List<String> numbers);

    void updateSign(SignDTO signDTO);

    List<Sign> getByDornumber(String dorNumber);
}
