
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">
    <h1>addCar</h1>
   <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddCar">
       <div class="row">
           <div class="col-md-6 mb-3">
               <label for="license_plate">License Plate</label>
               <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" value="" required >
               <div class="invalid-feedback">
                   License Plate is required.
               </div>
           </div>
       </div>

       <div class="row">
           <div class="col-md-6 mb-3">
               <label for="parking_spot">Parking Spot</label>
               <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder="" value="" required >
               <div class="invalid-feedback">
                   Parking Spot is required.
               </div>
           </div>
       </div>

       <div class="row">
           <div class="col-md-6 mb-3">
           <label for="owner_id" >Owner Id</label>
           <select name="owner_id" id="owner_id" required="">
               <option value="">Choose...</option>
               <c:forEach var="user" items="${users}" varStatus="status">
                   <option value="${user.id}">${user.username}</option>
               </c:forEach>
           </select>


           <div class="invalid-feedback">
               Please select a valid owner
           </div>
          </div>
       </div>
           <button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
   </form>



</t:pageTemplate>
