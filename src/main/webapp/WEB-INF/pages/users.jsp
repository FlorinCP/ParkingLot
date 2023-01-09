<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
  <h1>Users</h1>
  <form method="post" action="${pageContext.request.contextPath}/Users" >

  <a href="${pageContext.request.contextPath}/AddUser" class="to btn btn-primary btn-lg">Add User</a>
    <button class="" type="submit">Invoice</button>

  <div class="container text-center">
    <c:forEach var="user" items="${users}">
      <div class="row">
        <div class="col">
          <input type="checkbox" name="user_ids" value="${user.id}" />
        </div>
        <div class="col">
            ${user.email}
        </div>
        <div class="col">
            ${user.username}
        </div>
        <div class="col">
            ${user.password}
        </div>
      </div>
    </c:forEach>
  </div>
  </form>
  <c:if test="${not empty invoices}">
    <h2>Invoices</h2>

    <!--  How to read this:
    - if the collection of usernames named invoices is not empty, display the following
    - for each username in invoices
    - display the current number in the list (information available from status.index, index starts from 0)
    - display the username
    - display a line break -->
    <c:forEach var = "username" items="${invoices}" varStatus="status">
      ${status.index + 1}.${username}
      <br/>
    </c:forEach>

  </c:if>
</t:pageTemplate>
