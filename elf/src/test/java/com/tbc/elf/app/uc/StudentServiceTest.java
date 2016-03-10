package com.tbc.elf.app.uc;

import com.tbc.elf.app.uc.model.Student;
import com.tbc.elf.app.uc.model.Teacher;
import com.tbc.elf.app.uc.service.StudentService;
import com.tbc.elf.app.uc.service.TeacherService;
import com.tbc.elf.base.BaseTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YangLiBo@HF
 * @since 2016年03月07日16:17:13
 */
public class StudentServiceTest extends BaseTests {
    @Resource
    StudentService studentService;
    @Resource
    TeacherService teacherService;

    private Log LOG = LogFactory.getLog(StudentServiceTest.class);

    @Test
    @Rollback(false)
    public void testSingleMTO() {
        Set<Student> students = new HashSet<Student>(2);
        Student student = new Student();
        student.setId("y123456");
        student.setName("n123456");
        students.add(student);

        studentService.save(student);
        Student student1 = new Student();
        student1.setId("y222456");
        student1.setName("n222456");
        students.add(student1);
        studentService.save(student1);
        Teacher teacher = new Teacher();
        teacher.setTeacherId("t11456");
        teacher.setName("tn145");
        teacher.setStudents(students);
        teacherService.save(teacher);


    }

    @Test
    public void testDoubleMTO() {
        LOG.warn("+++++++++++++++++++++++++++++++++++");
        /*Teacher teacher = teacherService.load("1");
        teacher.getStudents();
        System.out.println(teacher);*/
        Student student = studentService.get("3");


        LOG.warn("+++++++++++++++++++++++++++++++++++");
        //studentService.update(student);
    }

    @Test
    public void testBatchSaveOrUpdate() {
        Set<Student> students = new HashSet<Student>(100);
        for (int i = 0; i < 1000000; i++) {
            Student student = new Student();
            student.setId(i + "");
            student.setName(i + "");
            student.setName1(i+"");
            student.setName2(i+"");
            student.setName3(i+"");
            student.setName4(i+"");
            students.add(student);
        }
        Date d = new Date();
        LOG.warn("----------------------------------------------------" + d.getTime());
        Date d1 = new Date();
        LOG.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++" + (d1.getTime() - d.getTime()));
    }


}
