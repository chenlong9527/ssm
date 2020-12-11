<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/4
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/queryDept.do",
                dataType: "json",
                success: function (data) {
                    /* var list=JSON.parse(data);*/
                    var options = "";
                    if (data != null) {
                        options = "<option value='0'>--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            options += "<option value='" + data[i].deptno + "'>" + data[i].dname + "</option>";
                        }
                        $("#dept").html(options);
                    }
                }
            })

            /* $("#btn").click(function () {
                 $.ajax({
                     type:"get",
                     url:"

            ${pageContext.request.contextPath}/empc/saveOrUpdate.do",
                    data:{
                        ename:$("#ename").val(),
                        bdate:$("#bdate").val(),
                        sex:$("input[name='sex']:checked").val(),
                        city:$("#city").val(),
                        deptno:$("#dept").val()
                    },
                    success:function (data) {
                        if(data==1){
                            alert("添加成功");
                        }else{
                            alert("添加失败");
                        }
                    }
                })
            })*/
        });

    </script>

</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/insert.do" method="post" enctype="multipart/form-data">
        姓名： <input type="text" name="ename" id="ename"><br>
        日期： <input type="date" name="bdate" id="bdate"><br>
        性别： <input type="radio" name="sex" value="M">男
        <input type="radio" name="sex" value="F">女<br>
        城市： <input type="text" name="city" id="city"><br>
        部门：<select id="dept" name="deptno">
    </select><br>
        图片： <input type="file" name="fileLoad"><br>
        <input type="submit" value="提交">
        <%-- <button  name="btn" id="btn">提交</button>--%>
    </form>
</div>
</body>
</html>
