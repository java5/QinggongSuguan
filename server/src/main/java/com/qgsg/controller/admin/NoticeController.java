package com.qgsg.controller.admin;

import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.dto.NoticeDTO;
import com.qgsg.dto.NoticePageQueryDTO;
import com.qgsg.entity.Notice;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.NoticeService;
import com.qgsg.vo.NoticeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/notice")
@Api(tags = "公告相关接口")
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    /**
     * 公告分页查询
     * @param noticePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("公告分页查询")
    public Result<PageResult> page(NoticePageQueryDTO noticePageQueryDTO){
        log.info("公告分页查询：{}",noticePageQueryDTO);
        PageResult pageResult=noticeService.page(noticePageQueryDTO);
        return Result.success(pageResult);

    }
    /**
     * 新增公告
     * @param noticeDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("新增公告")
    public Result save(@RequestBody NoticeDTO noticeDTO){
        log.info("新增公告：{}",noticeDTO);
        noticeService.save(noticeDTO);
        return Result.success();
    }
    /**
     * 修改宿舍
     * @param noticeDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改公告")
    public Result update(@RequestBody NoticeDTO noticeDTO){
        log.info("修改公告:{}",noticeDTO);
        noticeService.update(noticeDTO);
        return Result.success();
    }
    /**
     * 公告删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("公告删除")
    public Result delete(@PathVariable int id){
        log.info("公告删除：{}",id);
        noticeService.deleteBatch(id);
        return Result.success();
    }
    /**
     * 首页公告展示
     */
    @GetMapping("/homePageNotice")
    @ApiOperation("首页公告分页查询")
    public Result<List<Notice>> homePageNotice() {
        List<Notice> list = noticeService.homePageNotice();
        log.info("首页公告查询");
            return Result.success(list);

    }
}
