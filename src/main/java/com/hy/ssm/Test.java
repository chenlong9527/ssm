package com.hy.ssm;

import com.hy.ssm.mapper.EmpMapper;
import com.hy.ssm.pojo.Emp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext App=new ClassPathXmlApplicationContext("Spring.xml");
        EmpMapper studentMapper =(EmpMapper)App.getBean("empMapper");
        Emp emp = new Emp();
        emp.setEname("哈哈哈");
        System.out.println(emp.getDeptno());
        studentMapper.insert(emp);
    }
}
