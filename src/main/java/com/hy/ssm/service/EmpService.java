package com.hy.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.ssm.mapper.DeptMapper;
import com.hy.ssm.mapper.EmpMapper;
import com.hy.ssm.pojo.DeptBean;
import com.hy.ssm.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService extends ServiceImpl<EmpMapper,Emp> {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;

    /*
    * 组合查询
    * */
    public List<Emp> query(Emp emp){
        return empMapper.query(emp);
    }

    /*
    * 查询部门
    * */
    public List<DeptBean> queryDept(){
        return deptMapper.queryDept();
    }

    public int insert(Emp emp){
     return    empMapper.insert(emp);
    }

    public Emp queryById(Integer eid){
      return   empMapper.queryById(eid);
    }

    public void  delete(Integer eid){
        empMapper.delete(eid);
    }

    public void delMany(int[] eid){
       empMapper.delMany(eid);
    }

    /*
     * 查询所有
     * */

    public IPage<Emp> queryPage(Integer page,Integer limit,Emp emp) {
        if(page==null){
            page=1;
        }
       // Integer limit=2;
        IPage<Emp> p = new Page<>(page, limit);
        p = empMapper.queryAll(p, emp);
        //p.getRecords().forEach(System.out::println);
        return p;
    }
}
