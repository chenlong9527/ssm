package com.hy.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.ssm.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
//    @Results({
//            @Result(column = "dname",property = "deptBean.dname")
//    })
//    @Select("select * from emp e,dept d where e.deptno=d.deptno")
//    public List<Emp> queryAll();


    @Results({
            @Result(column = "dname",property = "deptBean.dname")
    })
    @SelectProvider(type = EmpProvider.class,method = "query_sel")
    public List<Emp> query(Emp emp);

    /*
    * 添加
    * */
    @SelectKey(statement = "select last_insert_id()",before = true,resultType = int.class,keyColumn ="eid" ,keyProperty ="eid" )
    @Insert("insert into emp values(#{eid},#{ename},#{bdate},#{sex},#{city},#{deptno},#{photo})")
    public int insert(Emp emp);

    @Select("select * from emp where eid=#{value}")
    public Emp queryById(Integer id);

    @Delete("delete from emp where eid=#{value}")
    public void delete(Integer id);

    public void delMany(int[] eid );


    @Results({
            @Result(column = "dname",property = "deptBean.dname")
    })
    @SelectProvider(type = EmpProvider.class,method = "query_selAll")
    public IPage<Emp> queryAll(IPage<Emp> page,@Param("emp") Emp emp);


}
