package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.vo.SignVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {

    Page<SignVO> signpage(SignDTO studentPageQueryDTO);
}
