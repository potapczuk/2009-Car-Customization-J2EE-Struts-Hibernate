<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Pedido</h2>

<html:form styleClass="formulario">
	<fieldset>
        <legend>Informações sobre o pedido</legend>
        <p>
            <strong>Código:</strong> ${PedidoForm.pedidoEntity.id}
        </p>
        <p class="ultimo">
            <strong>Data:</strong> <fmt:formatDate pattern="dd/MM/yyyy" value="${PedidoForm.pedidoEntity.dia}" />
        </p>
    </fieldset>
    <fieldset>
        <legend>Informações sobre o carro montado</legend>
        <p>
            <strong>Carro:</strong> ${PedidoForm.pedidoEntity.modelo.carro.marca.nome} ${PedidoForm.pedidoEntity.modelo.carro.nome}
        </p>
        <p>
            <strong>Modelo:</strong> ${PedidoForm.pedidoEntity.modelo.nome}
        </p>
        <p>
            <strong>Pintura:</strong> ${PedidoForm.pedidoEntity.pintura.nome}
        </p>
        <p>
            <strong>Acessórios:</strong>
            <c:forEach var="acessorio" items="${PedidoForm.pedidoEntity.acessorios}">
                <br/>${acessorio.nome}
            </c:forEach>
        </p>
        <p class="ultimo">
            <strong>Valor Total:</strong> R$ <fmt:formatNumber value="${PedidoForm.pedidoEntity.valor_total}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" />
        </p>
    </fieldset>
    <fieldset>
        <legend>Informações sobre o cliente</legend>
        <p>
            <strong>Nome:</strong> ${PedidoForm.pedidoEntity.nome}
        </p>
        <p>
            <strong>Cidade:</strong> ${PedidoForm.pedidoEntity.cidade}
        </p>
        <p>
            <strong>Estado:</strong> ${PedidoForm.pedidoEntity.estado}
        </p>
        <p>
            <strong>CEP:</strong> ${PedidoForm.pedidoEntity.cep}
        </p>
        <p>
            <strong>Telefone:</strong> ${PedidoForm.pedidoEntity.telefone}
        </p>
        <p class="ultimo">
            <strong>E-Mail:</strong> ${PedidoForm.pedidoEntity.email}
        </p>
    </fieldset>
</html:form>