<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <h3>Loan Information:</h3>
    
    Loan debt: <i><c:out value="${model.products.getDebt()}"/></i><br/>
    Payment Period: <i><c:out value="${model.products.getPaymentPeriod()}"/></i><br/>
    Num Fees: <i><c:out value="${model.products.getAmortizationTime()}"/></i><br/>
    Openning Comission: 0.0
    
	<h3>Amortization Table:</h3>
      Loan fees: <i><c:out value="${model.products.calcPayments()}"/></i><br/>
      
     <%--  <c:forEach items="${model.products}" var="prod">
      <c:out value="${prod.description}"/> <i>$<c:out value="${prod.price}"/></i><br><br>
      </c:forEach> --%>
      
      
    <br>
    <button type="button" onClick="location.href='<c:url value="feeLimits.htm"/>'">Change number of Fees</button>
    <br>
  </body>
</html>