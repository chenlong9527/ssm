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
        $(function(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/queryDept.do",
                dataType:"json",
                success:function (data) {
                    /* var list=JSON.parse(data);*/
                    var options="";
                    var deptno=$("#deptno").val();
                    if(data!=null){
                        options="<option value='0'>--请选择--</option>";

                        for(var i=0;i<data.length;i++){
                            options+="<option "+(deptno==data[i].deptno?'selected':'')+" value='"+data[i].deptno+"'>"+data[i].dname+"</option>";
                        }
                        $("#dept").html(options);
                    }
                }
            })

           /* $("#btn").click(function () {
                $.ajax({
                    type:"get",
                    url:"${pageContext.request.contextPath}/empc/saveOrUpdate.do",
                    data:{
                        eid:$("#eid").val(),
                        ename:$("#ename").val(),
                        bdate:$("#bdate").val(),
                        sex:$("input[name='sex']:checked").val(),
                        city:$("#city").val(),
                        deptno:$("#dept").val()
                    },
                    success:function (data) {
                        if(data=="1"){
                            alert("修改成功");
                        }else{
                            alert("修改失败");
                        }
                    }
                })
            })*/
        });

    </script>

</head>
<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/upd.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="eid" value="${emp.eid}" id="eid">
        姓名： <input type="text" name="ename" value="${emp.ename}" id="ename"><br>
        日期： <input type="date" name="bdate" value="${emp.bdate}" id="bdate"><br>
        性别： <input type="radio" name="sex" value="M" ${emp.sex=='M'?'checked':''}>男
        <input type="radio" name="sex" value="F" ${emp.sex=='F'?'checked':''}>女<br>
        城市： <input type="text" name="city" value="${emp.city}" id="city"><br>
        <input type="hidden" value="${emp.deptno}" id="deptno">
        部门：<select id="dept" name="dept">

        </select><br>
        <input type="hidden" value="${emp.photo}" name="photo">
        照片：<img src="${pageContext.request.contextPath}/photos/${emp.photo}" style="height: 30px;width: 30px"><br>
        <input type="file" name="fileLoad"><br>
       <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
