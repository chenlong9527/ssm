package com.hy.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.ssm.pojo.DeptBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<DeptBean> {
    @Select("select * from dept")
    public List<DeptBean> queryDept();
}
