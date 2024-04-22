package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.context.BaseContext;
import com.qgsg.dto.NoticeDTO;
import com.qgsg.dto.NoticePageQueryDTO;
import com.qgsg.entity.Notice;
import com.qgsg.entity.Teacher;
import com.qgsg.mapper.NoticeMapper;
import com.qgsg.mapper.TeacherMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.NoticeService;
import com.qgsg.vo.NoticeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    /**
     * 公告分页查询
     * @param noticePageQueryDTO
     * @return
     */
    @Override
    public PageResult page(NoticePageQueryDTO noticePageQueryDTO) {
        PageHelper.startPage(noticePageQueryDTO.getPage(),noticePageQueryDTO.getPageSize());
        Page<NoticeVO> noticeVO=noticeMapper.selectpage(noticePageQueryDTO);
        return new PageResult(noticeVO.getTotal(),noticeVO.getResult());
    }
    /**
     * 首页公告展示
     */
    @Override
    public List<Notice> homePageNotice() {
        List<Notice> noticeList = noticeMapper.selectList();
        return noticeList;
    }

    /**
     * 新增公告
     * @param noticeDTO
     */
    @Override
    public void save(NoticeDTO noticeDTO) {
        Long teacherId = BaseContext.getCurrentId();
        Teacher teacher=teacherMapper.selectById(teacherId);
        Notice notice=new Notice();
        BeanUtils.copyProperties(noticeDTO,notice);
        notice.setAuthor(teacher.getName());
        notice.setReleaseTime(LocalDateTime.now());
        noticeMapper.insert(notice);
    }

    /**
     * 修改公告
     * @param noticeDTO
     */
    @Override
    public void update(NoticeDTO noticeDTO) {
        Long teacherId = BaseContext.getCurrentId();
        Teacher teacher=teacherMapper.selectById(teacherId);
        Notice notice=new Notice();
        BeanUtils.copyProperties(noticeDTO,notice);
        notice.setAuthor(teacher.getName());
        notice.setReleaseTime(LocalDateTime.now());
        noticeMapper.updateNotice(notice);
    }

    /**
     * 公告删除
     * @param id
     */
    @Override
    public void deleteBatch(int id) {
        noticeMapper.deleteById(id);
    }
}
