<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="marca">
	<h1><a href="<c:url value="/Home.do" />">Indiana veículos</a></h1>
</div>
<div id="menu-superior">
	<ul>
		<li>
			<a href="<c:url value="/Home.do" />" class="primeiro">Home</a>
		</li>
		<li class="separador">
		</li>
		<li>
			<a href="<c:url value="#" />">A Indiana</a>
		</li>
		<li class="separador">
		</li>
		<li>
			<a href="<c:url value="#" />">QCP</a>
		</li>
		<li class="separador">
		</li>
		<li>
			<a href="<c:url value="#" />" class="ultimo">Contato</a>
		</li>
	</ul>
</div>