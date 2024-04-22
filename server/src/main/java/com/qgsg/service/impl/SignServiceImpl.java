package com.qgsg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.dto.SignDTO;
import com.qgsg.dto.SignPageQueryDTO;
import com.qgsg.entity.Sign;
import com.qgsg.exception.InsufficientCapacityException;
import com.qgsg.mapper.SignMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.SignService;
import com.qgsg.vo.SignVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public PageResult page1(SignDTO signDTO) {
        PageHelper.startPage(signDTO.getPage(),signDTO.getPageSize());
        Page<SignVO> signVO = signMapper.signpage1(signDTO);
        return new PageResult(signVO.getTotal(), signVO.getResult());
    }

    //根据number查询集合
    @Override
    public Page<SignVO> getByNumber(String number) {
        Page<SignVO> sign = signMapper.getByNumber(number);
        return sign;
    }

    @Override
    public void deleteSign1(List<String> numbers) {
        log.info("number:{}",numbers);
        for(String number:numbers){
            log.info(number);
            signMapper.deleteToSign1(number);
        }
    }

    @Override
    public void updateSign(SignDTO signDTO) {
        Sign sign = new Sign();
        BeanUtils.copyProperties(signDTO,sign);
        log.info(String.valueOf(sign));
        signMapper.update(sign);
    }

    @Override
    public List<Sign> getByDornumber(String dorNumber) {
        List<Sign> sign=signMapper.getByDornumer(dorNumber);
        return sign;
    }

    /**
     * 导出excel签到数据表
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public void getexprot(HttpServletResponse response) throws IOException {

//要一行一行的组装数据，塞到一个1ist里面
//每一行数据，其实就对应数据库表中的一行数据，也就是对应Java的一个实体类Sign要映射数据，我们需要一个map<key,value>
//1.从数据库中查询出所有数据
        List<Sign> all= signMapper.selectAll();
        if(CollectionUtil.isEmpty(all)){
            throw new InsufficientCapacityException("未找到数据");
        }
//2.定义一个 List，存储处理之后的数据，用于塞到1ist里
        List<Map<String,Object>> list =new ArrayList<>();
//3.定义Map<key,value>出来，历每一条数据，然后封装到 Map<key,value>里，把这个 map塞进集合中
        //今日签到数据
        LocalDate beginTime = LocalDate.now();
        LocalDate endTime = LocalDate.now();
        Integer signNum=signMapper.selectToday(
                LocalDateTime.of(beginTime, LocalTime.MIN),
                LocalDateTime.of(endTime, LocalTime.MAX)
                ,1);
        Integer AllNum=signMapper.selectToday(
                LocalDateTime.of(beginTime, LocalTime.MIN),
                LocalDateTime.of(endTime, LocalTime.MAX),null);
        Integer notSignNum=AllNum-signNum;
        //今日签到率
        Double shang=signNum.doubleValue()/ AllNum.doubleValue();
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2); // 设置小数点后的最少位数
        String percent = percentFormat.format(shang);
        int a=0;
        for(Sign sign :all){
            Map<String,Object>row=new LinkedHashMap<>();
            row.put("学号",sign.getNumber());
            row.put("姓名",sign.getName());
            row.put("签到状态",sign.getSignStatus() ==1?"已签到":"未签到");
            row.put("宿舍号",sign.getDormitoryNumber());
            row.put("签到时间",sign.getSignTime().toString());
            row.put("未签原因",sign.getReason() == 1 ? "迟到" :
                    ( sign.getReason() ==2 ? "晚归": ( sign.getReason() == 3 ? "请假" : "其它")));
            row.put("具体原因",sign.getSpecificReason());
            if(a==0){
                row.put("今日签到人数",signNum);
                row.put("今日未签到人数",notSignNum);
                row.put("今日总人数",AllNum);
                row.put("今日签到率",percent);
            }
            list.add(row);
            a++;
        }
        //4.创建一个 Excelwriter，把 list 数据用这个writer写出来(生成出来)
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list,true);
        //5.把这个 excel 下载下来

        response.setContentType("application/vnd,openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8") ;
        response.setHeader("Content-Disposition","attachment;filename= "+new String("签到数据表.xlsx".getBytes(),"ISO-8859-1"));
        ServletOutputStream out=response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        IoUtil.close(System.out);
    }



    /**
     * 签到表分页查询
     * @param signPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(SignPageQueryDTO signPageQueryDTO) {
        PageHelper.startPage(signPageQueryDTO.getPage(),signPageQueryDTO.getPageSize());
        Page<SignVO> signVO = signMapper.signpage(signPageQueryDTO);
        return new PageResult(signVO.getTotal(), signVO.getResult());
    }
    /**
     * 根据id删除签到表
     * @param id
     * @return
     */
    @Override
    public void deleteSign(int id) {
        signMapper.deleteToSign(id);
    }




}
