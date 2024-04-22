package com.qgsg.service;

import com.qgsg.dto.NoticeDTO;
import com.qgsg.dto.NoticePageQueryDTO;
import com.qgsg.entity.Notice;
import com.qgsg.result.PageResult;
import com.qgsg.vo.NoticeVO;

import java.util.List;

public interface NoticeService {
    /**
     * 公告分页查询
     * @param noticePageQueryDTO
     * @return
     */
    PageResult page(NoticePageQueryDTO noticePageQueryDTO);
    /**
     * 首页公告展示
     */
    List<Notice> homePageNotice();

    /**
     * 新增公告
     * @param noticeDTO
     */
    void save(NoticeDTO noticeDTO);

    /**
     * 修改公告
     * @param noticeDTO
     */
    void update(NoticeDTO noticeDTO);

    /**
     * 公告删除
     * @param id
     */
    void deleteBatch(int id);
}
