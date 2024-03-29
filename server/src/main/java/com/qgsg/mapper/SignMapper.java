package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.entity.Sign;
import com.qgsg.entity.Student;
import com.qgsg.vo.SignVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {

    void update(Sign sign);

    Page<SignVO> signpage(SignDTO studentPageQueryDTO);
}
