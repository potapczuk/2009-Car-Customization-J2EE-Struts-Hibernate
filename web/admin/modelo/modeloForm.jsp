<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<c:if test="${ModeloForm.id == null || ModeloForm.id < 1}">
	<h2>Novo Modelo</h2>
</c:if>
<c:if test="${ModeloForm.id > 0}">
	<h2>Alterar Modelo</h2>
</c:if>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<html:form action="/Modelo" focus="nome" styleClass="formulario">
	<html:hidden property="id" />
    <input type="hidden" name="id_carro" value="${carro.id}" />
    <fieldset>
        <legend><bean:message key="form.introducao"/></legend>
        <p>
            <label for="fnome"><span>Nome:</span></label>
            <html:text styleId="fnome" property="nome" maxlength="100" size="30"></html:text>
        </p>
        <p>
            <label for="fnome"><span>Descrição:</span></label>
            <html:textarea styleId="fdescricao" property="descricao" cols="60" rows="6"></html:textarea>
        </p>
        <p>
            <label for="fnome"><span>Preço:</span></label>
            <html:text styleId="fpreco" property="preco" maxlength="15" size="15"></html:text>
        </p>
        <p class="botoes">
            <button type="submit" name="acao" value="salvar" class="primeiro-botao">Salvar</button>
            <button type="submit" name="acao" value="cancelar">Cancelar</button>
        </p>
    </fieldset>
</html:form>