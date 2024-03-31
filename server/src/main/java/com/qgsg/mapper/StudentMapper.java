package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.vo.StudentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 插入学生数据
     * @param student
     */
    void insert(Student student);

    /**
     * 修改学生数据
     * @param student
     */
    void update(Student student);


    /**
     * 学生分页查询
     * @param studentPageQueryDTO
     * @return
     */
    Page<StudentVO> pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 根据学号查询学生
     * @param number
     * @return
     */
    @Select("select * from student where number = #{number}")
    Student getByNumber(String number);

    /**
     * 根据id删学生
     * @param number
     */
    @Delete("delete from student where number=#{number}")
    void deleteByNumber(String number);

    //根据宿舍id查询学生
    List<Long> select(List<Long> dormitoryIds);

    void updateDormitoryNumber(String dormitoryNumber);
    //查询所有学生信息用于某些学生关联了所修改的宿舍也进行修改新的宿舍号
    @Select("select * from student ")
    List<Student> selectAll();

    @Insert("insert into sign (id,number, name, sign_status, sign_time,dormitory_number) " +
            "values " +
            "(#{id},#{number},#{name},#{signStatus},#{signTime},#{dormitoryNumber})")
    void insertsign(Student student);

    /**
     * 每天跟新状态
     * @param signStatus
     * @return
     */
//    @Update("update student set sign_status= #{signStatus}")
//    List<Student> getBystatusSign(int signStatus);

    /**
     * 根据学生签到状态查询学生
     * @param signStatus
     * @return
     */
    @Select("select * from student where sign_status=#{signStatus}")
    List<Student> getBystatusSign(int signStatus);
}
