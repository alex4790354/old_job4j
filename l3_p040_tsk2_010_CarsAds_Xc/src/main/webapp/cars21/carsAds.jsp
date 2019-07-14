<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.cars21.beans.CarAdsStorage" %>
<%@ page import="ru.job4j.cars21.models.CarAd" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="ru.job4j.cars21.component.Ads" %>
<%@ page import="ru.job4j.cars21.component.CarAds" %>
<%@ page import="ru.job4j.cars21.models.CarBrand" %>
<%@ page import="ru.job4j.cars21.beans.CarBrandsStorage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
   <title>Все авто на продажу</title>

   <!-- Bootstrap CSS Tables Reference: https://www.w3schools.com/bootstrap/bootstrap_ref_css_tables.asp -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

webapp/cars21/carsAds.jsp <br>

<% String error = request.getParameter("message"); %>
<c:if test="${error != ''}">
   <div style="background-color: red">
         ${error}
   </div><br>
</c:if>

<% String message = request.getParameter("message"); %>
<c:if test="${message != ''}">
   <h1>${message}</h1><br>
</c:if>


<% ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
   Ads<CarAd> ads = context.getBean(CarAdsStorage.class);
   CarBrandsStorage carBrands = context.getBean(CarBrandsStorage.class);
   List<CarAd> listAds = ads.getAllObject();


%>

<div class="container">
   <h2>Cars for sale</h2>
   <p>All cars for sale list</p>
   <div class="table-responsive">

      <table class="table"><thead>
      <tr>
         <td>ID:</td>
         <td>Марка ID</td>
         <td>Марка</td>

         <td>ID</td>
         <td>Пробег</td>
         <td>Описание</td>
         <td>фото</td>
         <td></td>
      </tr></thead>
         <tr><td></td></tr>
         <% for (CarAd carsAd : listAds) { %>
         <%--<c:forEach items="<%=listAds%>" var="carAd">--%>
         <tbody>
         <tr>
            <%--<form action="${pageContext.servletContext.contextPath}/createedit02" method='post'>--%>
            <form action="${pageContext.servletContext.contextPath}/carAd_JSTL.jsp?adId=<%=carsAd.getId() %>" method='get'>
               <input type="hidden" value="<%=carsAd.getId() %>" name="adId">
               <td><%=carsAd.getId() %></td>
               <td><%=carsAd.getCarsBrandId() %></td>
               <td><%=carBrands.getById(carsAd.getCarsBrandId()).getName() %></td>

               <td><%=carsAd.getUserId() %></td>
               <td><%=carsAd.getMileage() %></td>
               <td><%=carsAd.getDescription() %></td>
               <td><img src="${pageContext.servletContext.contextPath}/images/cars/<%=carsAd.getPhotoId() %>.jpg" alt="Smiley face" height="100"></td>
               <td><input type="submit" value="Details">
            </form>
            <form action="${pageContext.servletContext.contextPath}/check/soldout02?adId=<%=carsAd.getId() %>&status=N&userid=<%=carsAd.getUserId() %>" method='post'>
               <input type="hidden" value="<%=carsAd.getId() %>" name="adId">
               <input type="hidden" value="<%=carsAd.getUserId() %>" name="userid">
               <input type="hidden" value="N" name="status">
               <br>
               <input type="submit" value="Sold out"></td>
               </td>
            </form>
         </tr>
         <% } %>

         <tr><td><form action="${pageContext.servletContext.contextPath}/" method='get'>
            <input type="submit" value="New Car">
         </form></tr></td>

         </tbody>
      </table>
   </div></div>

</body>
</html>

