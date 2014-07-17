<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Monte seu carro</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<div class="pinturas">
    <h3>Escolha a Pintura</h3>
    <html:form action="/Monte">
        <html:hidden property="etapa" value="pintura" />
        <html:hidden property="carro" />
        <html:hidden property="modelo" />

        <c:forEach var="tpinturas" items="${lpinturas}" varStatus="id">
            <div class="tipoDePintura">
            <c:forEach var="pintura" items="${tpinturas}" varStatus="id">
                <c:if test="${id.first}">
                    <strong>${pintura.tipoDePintura.nome}</strong><br/>
                    <p class="preco">R$ <fmt:formatNumber value="${pintura.tipoDePintura.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></p>
                </c:if>
                <html:radio property="pintura" value="${pintura.id}"/> <span><label>${pintura.nome}</label></span><br/>
            </c:forEach>
            </div>
        </c:forEach>

        <div class="botoes direita">
            <button type="submit" name="acao" value="escolherAcessorios">Continuar</button>
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
    <p class="pintura"><strong>Pintura:</strong><br/></p>
    <p class="acessorios"><strong>Acessorios:</strong><br/></p>
    <p class="total">
        <span class="total-titulo">Total</span>
        <span class="valor">R$ <fmt:formatNumber value="${modelo.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></span>
    </p>
</div>

<div class="clear"></div>