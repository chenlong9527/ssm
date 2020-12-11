<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2020/12/7
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
    <script type="text/javascript" src="../layui/layui.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<form class="layui-form" lay-filter="fromDemo" action="">
    <input type="hidden" name="eid" >
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="ename" id="ename" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-inline">
                <input type="text" name="bdate" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="sex">
                    <option value="">请选择</option>
                    <option value="M" this.value=='M'?'selected':''>男</option>
                    <option value="F" this.value=='F'?'selected':''>女</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">城市</label>
        <div class="layui-input-inline">
            <input type="text" name="city"  placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-inline">
                <select id="dept" name="deptno">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>
<script>
    layui.use(['form','layer'],function () {
        var form=layui.form
        ,$=layui.jquery
        ,layer=layui.layer

        //查询部门
        $.ajax({
            url:"/queryDept.do",
            dataType: "json",
            success:function (data) {
                for (var i=0;i<data.length;i++){
                    $("#dept").append(new Option(data[i].dname,data[i].deptno))
                }
                layui.form.render("select");//重新渲染
            }
        })


        var id=window.location.search;
        $.ajax({
            url:"/queById.do"+id,
            dataType:"json",
            success:function (data) {
                form.val('fromDemo',{
                    "eid":data.eid
                    ,"ename":data.ename
                    ,"bdate":data.bdate
                    ,"sex":data.sex
                    ,"city":data.city
                    ,"deptno":data.deptno
                })
            }
        })

        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            $.ajax({
                type:'post',
                url:'/empUpd.do',
                data:JSON.stringify(data.field),
                dataType: 'json',
                contentType:'application/json',
                success:function (data) {
                    if(data){
                        layer.msg("修改成功",{icon:1})
                        window.parent.location.reload();//修改成功后刷新父界面
                        layer.close(index);

                    }else{
                        layer.msg("修改失败",{icon:1})
                    }

                }
            })

            return false;
        })
    })
</script>

<body>

</body>
</html>
