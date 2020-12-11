package com.hy.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "emp")
public class Emp {

    @TableId(value = "eid",type = IdType.AUTO)
    private int eid;
    private String ename;
    private String bdate;
    private String sex;
    private String city;
    private Integer deptno;
    @TableField(exist = false)
    private com.hy.ssm.pojo.DeptBean deptBean;
    private String photo;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public com.hy.ssm.pojo.DeptBean getDeptBean() {
        return deptBean;
    }

    public void setDeptBean(com.hy.ssm.pojo.DeptBean deptBean) {
        this.deptBean = deptBean;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", bdate='" + bdate + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", deptno=" + deptno +
                ", deptBean=" + deptBean +
                ", photo='" + photo + '\'' +
                '}';
    }
}
