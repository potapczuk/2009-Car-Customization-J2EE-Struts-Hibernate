<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<li>
		<a href="<c:url value="/Carro.do" />" class="primeiro">Carros</a>
	</li>
	<li>
		<a href="<c:url value="/Pintura.do" />">Pinturas</a>
	</li>
	<li>
		<a href="<c:url value="/Acessorio.do" />">Acessórios</a>
	</li>
	<li>
		<a href="<c:url value="/Marca.do" />">Marcas</a>
	</li>
	<li>
		<a href="<c:url value="/Pedido.do" />" class="ultimo">Pedidos</a>
	</li>
</ul>  