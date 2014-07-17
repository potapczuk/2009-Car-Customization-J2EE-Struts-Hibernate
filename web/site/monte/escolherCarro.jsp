<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<h2>Monte seu carro de forma personalizada</h2>

<div class="carros">
<c:forEach var="carros" items="${lcarros}" varStatus="id">
    <c:if test="${!id.first}">
        <br/>
    </c:if>
    <c:forEach var="carro" items="${carros}" varStatus="id">
        <c:if test="${id.first}">
            <h3>${carro.marca.nome}</h3>
        </c:if>
        <div class="carro">
             <html:link page="/Monte.do?acao=escolherModelo&carro=${carro.id}">
                <span>${carro.nome}</span>
            </html:link>
        </div>
    </c:forEach>
</c:forEach>
</div>