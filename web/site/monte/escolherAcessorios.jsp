<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Monte seu carro</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<div class="acessorios">
    <h3>Escolha os Acessórios</h3>
    <html:form action="/Monte">
        <html:hidden property="etapa" value="acessorios" />
        <html:hidden property="carro" />
        <html:hidden property="modelo" />
        <html:hidden property="pintura" />

        <c:forEach var="tacessorios" items="${lacessorios}" varStatus="id">
            <div class="tipoDeAcessorio">
            <c:forEach var="acessorio" items="${tacessorios}" varStatus="id">
                <c:if test="${id.first}">
                    <p class="tipo">${acessorio.tipoDeAcessorio.nome}</p>
                </c:if>
                
                <html:multibox property="acessorios" value="${acessorio.id}"/>
                <label for="acessorios" class="checkbox">${acessorio.nome}</label>

                <p class="preco">R$ <fmt:formatNumber value="${acessorio.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></p>
                <p class="descricao">${acessorio.descricao}</p>
            </c:forEach>
            </div>
        </c:forEach>

        <div class="botoes direita">
            <button type="submit" name="acao" value="enviarPedido">Continuar</button>
        </div>
    </html:form>
</div>

<div class="resumo">
    <p class="meu-carro"><strong>Meu Carro</strong>${carro.marca.nome} ${carro.nome}</p>
    <p class="modelo">
        <strong>Modelo:</strong>
        ${modelo.nome} - R$ <fmt:formatNumber value="${modelo.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" />
    </p>
    <p class="pintura"><strong>Pintura:</strong>
        ${pintura.nome} - R$ <fmt:formatNumber value="${pintura.tipoDePintura.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" />
    </p>
    <p class="acessorios"><strong>Acessorios:</strong><br/></p>
    <p class="total">
        <span class="total-titulo">Total</span>
        <span class="valor">R$ <fmt:formatNumber value="${modelo.preco + pintura.tipoDePintura.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></span>
    </p>
</div>

<div class="clear"></div>