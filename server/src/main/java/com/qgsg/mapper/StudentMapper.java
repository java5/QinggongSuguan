package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.vo.StudentVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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



    @Select("select * from student where dormitory_number=#{dormitoryNumber}")
    List<Student> getByDornumer(String dormitoryNumber);


    /**
     * 主页顶部：学生统计
     */
    @Select("select count(id) from student")
    int selectAllNum();
    /**
     * 获取所有学生信息以及宿舍号
     * @return
     */
    @Select("select student.* ,dormitory.dormitory_number as dorNum from student left join dormitory on student.dormitory_id = dormitory.id where sign_status=1")
    List<Student> findAll();

    /**
     * 首页echarts（双条图）-各宿舍签到人数4.22
     */
    @Select("select count(id) from student where dormitory_number=#{dNs} and sign_status=1")
    Long selectSignNumber(String dNs);
    /**
     * 首页echarts（双条图）-宿舍未签到人数
     */
    @Select("select count(id) from student where dormitory_number=#{dNs} and sign_status=0")
    Long selectNotSignNumber(String dNs);
    /**
     * 首页echarts（条图）-根据宿舍集合查询个宿舍人数
     */
    @Select("select count(id) from student where dormitory_number=#{dNs}")
    Long selectByDormitoryNumber(String dNs);

    /**
     * 所有学生签到人数
     * @return
     */
    @Select("select count(id) from student where  sign_status=1")
    Long selectAllSignNumber();

    /**
     * 所有学生未签人数
     * @return
     */
    @Select("select count(id) from student where  sign_status=0")
    Long selectAlllNotSignNumber();

    /**
     * 根据学号查询学生
     * @param number
     * @return
     */
    @Select("select * from student where number=#{number}")
    Student selectByNumber(String number);
    @Select("select * from student where id=#{id}")
    Student selectById(int id);
}
