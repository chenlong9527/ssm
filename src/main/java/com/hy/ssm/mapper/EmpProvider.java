package com.hy.ssm.mapper;

import com.hy.ssm.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public class EmpProvider {
    public String query_sel( Emp emp){
        StringBuffer sql = new StringBuffer("select * from emp e,dept d where e.deptno=d.deptno ");
        if(emp.getEname()!=null && emp.getEname()!=""){
          sql.append(" and e.ename=#{ename}");
        }
        if(emp.getSex()!=null && emp.getSex()!="" ){
            sql.append(" and e.sex=#{sex}");
        }
        if(emp.getDeptno()!=null && emp.getDeptno()!=0 ){
            sql.append(" and e.deptno=#{deptno}");
        }
        return sql.toString();
    }

    public String query_selAll(@Param("emp") Emp emp){
        StringBuffer sql = new StringBuffer("select e.eid,e.ename,e.bdate,e.sex,e.city,e.deptno ee,e.photo,d.deptno de,d.dname from emp e,dept d where e.deptno=d.deptno ");
        if(emp!=null){
            if(emp.getEname()!=null && emp.getEname()!=""){
                sql.append(" and e.ename=#{emp.ename}");
            }
            if(emp.getSex()!=null && emp.getSex()!="" ){
                sql.append(" and e.sex=#{emp.sex}");
            }
            if(emp.getDeptno()!=null && emp.getDeptno()!=0 ){
                sql.append(" and e.deptno=#{emp.deptno}");
            }
        }

        System.out.println(sql.toString());
        return sql.toString();
    }


}
