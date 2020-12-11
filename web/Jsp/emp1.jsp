<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/3
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                   if(data!=null){
                       options="<option value='0'>--请选择--</option>";
                       for(var i=0;i<data.length;i++){
                           options+="<option value='"+data[i].deptno+"'>"+data[i].dname+"</option>";
                       }
                       $("#dept").html(options);
                   }
               }
           })

            $("#allCheck").click(function () {
              var x=$("#allCheck").prop('checked');
                $("input.empInfo").prop("checked",x);
            })

        });

    </script>

</head>
<body>
<form  action="${pageContext.request.contextPath}/queryAll.do" method="post">
    姓名：<input type="text" name="ename"> &nbsp;
    性别:<select name="sex">
     <option value="">请选择</option>
      <option value="M">男</option>
      <option value="F">女</option>
     </select>&nbsp;
部门：<select id="dept" name="deptno">
</select>
    <input type="submit" value="查询">
</form>

<button><a href="${pageContext.request.contextPath}/ins_redirect.do">添加</a></button>
<form action="${pageContext.request.contextPath}/delMany.do" method="post">
<table>
    <tr>
        <td><input type="checkbox" name="allCheck" id="allCheck">全选</td>
        <td>编号</td>
        <td>姓名</td>
        <td>日期</td>
        <td>性别</td>
        <td>城市</td>
        <td>部门</td>
        <td>照片</td>
        <td>操作</td>
    </tr>
<c:forEach varStatus="sta" var="emp" items="${list.getRecords()}">
    <tr>
        <td><input type="checkbox" class="empInfo" name="eid" value="${emp.eid}"></td>
        <td>${sta.index+1}</td>
        <td>${emp.ename}</td>
        <td>${emp.bdate}</td>
        <td>${emp.sex=='M'?'男':'女'}</td>
        <td>${emp.city}</td>
        <td>${emp.deptBean.dname}</td>
        <td><img src="${pageContext.request.contextPath}/photos/${emp.photo}" style="height: 30px;width: 30px"></td>
        <td><a href="${pageContext.request.contextPath}/queryById.do?eid=${emp.eid}">修改</a>|<a href="${pageContext.request.contextPath}/delete.do?eid=${emp.eid}">删除</a> </td>
    </tr>

</c:forEach>

    <tr align="center">
        <td colspan="9">
            <a href="/queryAll.do?page=${list.getCurrent()-1}">上一页</a>   |
            <a href="/queryAll.do?page=${list.getCurrent()+1}">下一页</a>
        </td>
    </tr>
</table><br>
<input type="submit" value="删除">
</form>
<%--<form action="${pageContext.request.contextPath}/empc/test.do" method="post">
    <input type="submit" value="提交">
</form>--%>

</body>
</html>
