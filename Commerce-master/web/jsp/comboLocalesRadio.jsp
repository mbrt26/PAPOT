<html>
<%@ taglib uri="/WEB-INF/tlds/listaLocalTodos" prefix="combo" %>
<head>
<title></title>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse"
bordercolor="#111111" width="30%">
<combo:listaLocalTodos>
  <tr>
    <td width="10%" nowrap><p align="center"><font face="Verdana" size="1">
    <input type="radio" name="idLocal" value="<%=idLocalVar%>"> </font></td>
    <td width="90%" nowrap><p align="left"><font face="Verdana" size="1"><%=nombreLocalVar%></font></td>
  </tr>
</combo:listaLocalTodos>
</table>
</body>
</html>