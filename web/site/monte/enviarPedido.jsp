<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Monte seu carro</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<div class="pedido">
    <h3>Fazer pedido</h3>
    <html:form action="/Monte">
        <html:hidden property="etapa" value="pedido" />
        <html:hidden property="carro" />
        <html:hidden property="modelo" />
        <html:hidden property="pintura" />

        <p class="legend">Preencha o formulário abaixo para enviar o pedido do seu carro montado para a INDIANA, e ela entrara em contato com você.</p>
        <p>
            <label for="fnome"><span>Nome:</span></label>
            <html:text styleId="fnome" property="nome" maxlength="100" size="40"></html:text>
        </p>
        <p>
            <label for="fcidade"><span>Cidade:</span></label>
            <html:text styleId="fcidade" property="cidade" maxlength="100" size="40"></html:text>
        </p>
        <p>
            <label for="festado"><span>Estado:</span></label>
            <html:select property="estado">
                <html:options property="estados" />
            </html:select>
        </p>
        <p>
            <label for="fcep"><span>CEP:</span></label>
            <html:text styleId="fcep" property="cep" maxlength="100" size="40"></html:text>
        </p>
        <p>
            <label for="ftelefone"><span>Telefone:</span></label>
            <html:text styleId="ftelefone" property="telefone" maxlength="100" size="40"></html:text>
        </p>
        <p>
            <label for="femail"><span>E-mail:</span></label>
            <html:text styleId="femail" property="email" maxlength="100" size="40"></html:text>
        </p>
        <p class="aceito"><input name="aceito" value="1" type="checkbox" id="faceito" /><label for="faceito">Aceito que a INDIANA entre em contato comigo</label></p>
        <div class="botoes direita">
            <button type="submit" name="acao" value="enviar">Continuar</button>
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
    <p class="acessorios"><strong>Acessorios:</strong>
        <c:forEach var="acessorio" items="${lacessorios}" varStatus="id">
            ${acessorio.nome} -
            R$ <fmt:formatNumber value="${acessorio.preco}"
                    type="currency" minFractionDigits="2"
                    maxFractionDigits="2" currencySymbol="" /><br/>
        </c:forEach>
    </p>
    <p class="total">
        <span class="total-titulo">Total</span>
        <span class="valor">R$ <fmt:formatNumber value="${total}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></span>
    </p>
</div>

<div class="clear"></div>