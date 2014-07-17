<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Monte seu carro</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<div class="modelos">
    <h3>Escolha o Modelo</h3>
    <html:form action="/Monte">
        <html:hidden property="etapa" value="modelo" />
        <html:hidden property="carro" />
        <c:forEach var="modelo" items="${modelos}" varStatus="id">
            <div class="modelo">
                <html:radio property="modelo" value="${modelo.id}"/> <span><label>${modelo.nome}</label></span>
                <p>${modelo.descricao}</p>
                <p>R$ <fmt:formatNumber value="${modelo.preco}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></p>
            </div>
        </c:forEach>
        <div class="botoes direita">
            <button type="submit" name="acao" value="escolherPintura">Continuar</button>
        </div>
    </html:form>
</div>

<div class="resumo">
    <p class="meu-carro"><strong>Meu Carro</strong>${carro.marca.nome} ${carro.nome}</p>
    <p class="modelo"><strong>Modelo:</strong><br/></p>
    <p class="pintura"><strong>Pintura:</strong><br/></p>
    <p class="acessorios"><strong>Acessorios:</strong><br/></p>
    <p class="total">
        <span class="total-titulo">Total</span>
        <span class="valor">R$ 0,00</span>
    </p>
</div>

<div class="clear"></div>