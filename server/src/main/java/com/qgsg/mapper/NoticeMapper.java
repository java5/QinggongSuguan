package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.NoticePageQueryDTO;
import com.qgsg.entity.Notice;
import com.qgsg.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /**
     * 公告分页查询
     * @param noticePageQueryDTO
     * @return
     */
    Page<NoticeVO> selectpage(NoticePageQueryDTO noticePageQueryDTO);
    /**
     * 首页公告展示
     */
    @Select("select * from notice")
    List<Notice> selectList();

    /**
     * 新增公告
     * @param notice
     */
    void insert(Notice notice);

    /**
     * 修改公告
     * @param notice
     */
    void updateNotice(Notice notice);

    /**
     * 删除公告
     * @param id
     */
    void deleteById(int id);
}
