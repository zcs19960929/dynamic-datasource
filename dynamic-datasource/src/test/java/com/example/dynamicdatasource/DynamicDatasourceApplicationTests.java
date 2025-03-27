package com.example.dynamicdatasource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dynamicdatasource.entity.AccountPO;
import com.example.dynamicdatasource.entity.OrderPO;
import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.entity.TeacherPO;
import com.example.dynamicdatasource.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DynamicDatasourceApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    void createStudent() {
        StudentPO studentPO = StudentPO.builder()
                .name("zhangsan")
                .age(16)
                .build();
        studentService.save(studentPO);
        System.out.println(studentPO);
    }

    @Test
    void queryStudent() {
        StudentPO studentPO = studentService.getOne(new LambdaQueryWrapper<StudentPO>()
                .eq(StudentPO::getName, "zhangsan")
                .last("limit 1"));
        System.out.println(studentPO);
    }

    @Test
    void createTeacher() {
        TeacherPO teacherPO = TeacherPO.builder()
                .name("teacher ma")
                .age(38)
                .build();
        teacherService.save(teacherPO);
        System.out.println(teacherPO);
    }

    @Test
    void queryTeacher() {
        TeacherPO teacherPO = teacherService.getOne(new LambdaQueryWrapper<TeacherPO>()
                .eq(TeacherPO::getName, "teacher ma")
                .last("limit 1"));
        System.out.println(teacherPO);
    }

    @Resource
    private TransactionalService transactionalService;

    @Test
    void testTransactional() {
        StudentPO studentPO = StudentPO.builder()
                .name("lisi")
                .age(16)
                .build();
        TeacherPO teacherPO = TeacherPO.builder()
                .name("teacher zhang")
                .age(38)
                .build();
        transactionalService.saveTeacherAndStudent(teacherPO, studentPO);
    }


    @Resource
    private AccountService accountService;

    @Test
    void testCreateAccount() {
        List<AccountPO> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            AccountPO accountPO = new AccountPO();
            accountPO.setName("aaa" + i).setTenantId((long) i);
            list.add(accountPO);
        }
        accountService.saveBatch(list);
        System.out.println(list);
    }

    @Test
    void testQueryAccount() {
        List<AccountPO> list = accountService.listByTenantId(6L);
        list.forEach(System.out::println);
        list = accountService.list();
        list.forEach(System.out::println);
    }

    @Test
    void testCreateOrderWithSnowflakeId() {
        List<OrderPO> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            OrderPO orderPO = new OrderPO();
            orderPO.setTenantId((long) i)
                    .setStatus("init")
                    .setDescription("test" + i)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
            list.add(orderPO);
        }
        orderService.saveBatch(list);
        System.out.println(list);
    }
}
