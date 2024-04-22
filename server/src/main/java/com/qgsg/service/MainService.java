package com.qgsg.service;

import com.qgsg.entity.Teacher;
import org.springframework.stereotype.Service;


public interface MainService {
    /**
     * 个人信息
     * @param id
     * @return
     */
    Teacher getMessage(Long id);
}
