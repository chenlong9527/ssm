package com.hy.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hy.ssm.LayuiData;
import com.hy.ssm.pojo.DeptBean;
import com.hy.ssm.pojo.Emp;
import com.hy.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
    * 组合查询
    * */
    @RequestMapping("/queryemp")
    public String query(Model model,Emp emp,Integer pagenum){
        if(pagenum==null){
            pagenum=1;
        }
        Page page = PageHelper.startPage(pagenum, 1, true);
        List<Emp> list = empService.query(emp);
        System.out.println(page.getTotal());
        model.addAttribute("list", list);
        return "/Jsp/emp.jsp";
    }

    /*
    * 查询部门表
    * */
    @RequestMapping("/queryDept")
    @ResponseBody
    public List<DeptBean> queryDept(){
        return empService.queryDept();
    }

    @RequestMapping("/insert")
    public String insert(Emp emp, @RequestParam("fileLoad")MultipartFile file, HttpServletRequest req) throws IOException {
        //获取文件名
        String filename=file.getOriginalFilename();
        //获取后缀
        String extname=filename.substring(filename.lastIndexOf("."));
        String pname= UUID.randomUUID().toString();
        File file1 = new File(req.getServletContext().getRealPath("/"));
        System.out.println(req.getServletContext().getRealPath("/"));
        String path=file1+"/photos/"+pname+extname;
        file.transferTo(new File(path));
        emp.setPhoto(pname+extname);
        empService.insert(emp);
        return "redirect:/queryemp.do";
    }

    @RequestMapping("/queryById")
    public String queryById(Integer eid,Model model){
       Emp emp=empService.queryById(eid);
       model.addAttribute("emp",emp);
       return "/Jsp/update.jsp";
    }

    @RequestMapping("/delete")
    public String delete(Integer eid){
        empService.delete(eid);
        return "redirect:/queryemp.do";
    }

    @RequestMapping("/delMany")
    public String delMany(@RequestParam("allCheck") int[] eid){
        empService.delMany(eid);
        return "redirect:/queryemp.do";
    }

    @RequestMapping("/upd")
    public String upd( @RequestParam("fileLoad")MultipartFile file,Emp emp,HttpServletRequest req) throws IOException {
        //获取文件名
        String filename=file.getOriginalFilename();
        if(filename!=null && filename!=""){
            //获取后缀
            String extname=filename.substring(filename.lastIndexOf("."));
            String pname= UUID.randomUUID().toString();
            File file1 = new File(req.getServletContext().getRealPath("/"));
            System.out.println(req.getServletContext().getRealPath("/"));
            String path=file1+"/photos/"+pname+extname;
            file.transferTo(new File(path));
            emp.setPhoto(pname+extname);
        }
        empService.updateById(emp);
        return "redirect:/queryemp.do";
    }

    @RequestMapping("/queryAll")
    public String queryAll(Integer page,Emp emp,Model model){
        IPage<Emp> list=empService.queryPage(page,1,emp);
        model.addAttribute("list",list);
       return "redirect:/queryemp.do";
    }

    @RequestMapping("/query")
    @ResponseBody
    public LayuiData query(Integer page,Integer limit,Emp emp){
        IPage<Emp> list=empService.queryPage(page,limit,emp);
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount(Integer.valueOf(String.valueOf(list.getTotal())));
        layuiData.setData(list.getRecords());
        return layuiData;
    }

    @RequestMapping("/ins_redirect")
    public String ins_redirect(){
        return "/Jsp/empAdd.jsp";
    }

    @RequestMapping("/delMore")
    @ResponseBody
    public boolean delMore(String ids){
        System.out.println("zou  ni ");
        try {
            empService.removeByIds(Arrays.asList(ids.split(",")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public boolean removeById(Integer id){
        try {
            empService.removeById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/empAdd")
    @ResponseBody
    public boolean empAdd(@RequestBody Emp emp){
        try {
            empService.save(emp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/queById")
    @ResponseBody
    public Emp queById(Integer id){

         return  empService.getById(id);

    }

    @RequestMapping("/empUpd")
    @ResponseBody
    public boolean empUpd(@RequestBody Emp emp){
        try {
            empService.updateById(emp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
