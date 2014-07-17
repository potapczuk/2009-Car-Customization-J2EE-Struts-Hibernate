<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<c:if test="${CarroForm.id == null || CarroForm.id < 1}">
	<h2>Novo Carro</h2>
</c:if>
<c:if test="${CarroForm.id > 0}">
	<h2>Alterar Carro</h2>
</c:if>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<html:form action="/Carro" focus="nome" styleClass="formulario">
	<html:hidden property="id" />
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
            <label for="fnome"><span>Marca:</span></label>
            <html:select property="marca" styleId="fmarca">
                <html:options collection="marcas" property="id" labelProperty="nome" />
            </html:select>
        </p>
        <div class="checkbox">
            <div class="left"><label><span>Pinturas disponíveis:</span></label></div>
            <div class="left">
            <c:forEach var="tpinturas" items="${lpinturas}" varStatus="id">
                <c:if test="${!id.first}">
                    <br/>
                </c:if>
                <c:forEach var="pintura" items="${tpinturas}" varStatus="id">
                    <c:if test="${id.first}">
                        <strong>${pintura.tipoDePintura.nome}</strong><br/>
                    </c:if>
                    <html:multibox property="pinturas" value="${pintura.id}"/>
                    <label for="pinturas" class="checkbox">${pintura.nome}</label><br/>
                </c:forEach>
            </c:forEach>
            </div>
            <div class="clear"></div>
        </div>
        <div class="checkbox">
            <div class="left"><label><span>Acessórios disponíveis:</span></label></div>
            <div class="left">
            <c:forEach var="tacessorios" items="${lacessorios}" varStatus="id">
                <c:if test="${!id.first}">
                    <br/>
                </c:if>
                <c:forEach var="acessorio" items="${tacessorios}" varStatus="id">
                    <c:if test="${id.first}">
                        <strong>${acessorio.tipoDeAcessorio.nome}</strong><br/>
                    </c:if>
                    <html:multibox property="acessorios" value="${acessorio.id}"/>
                    <label for="acessorios" class="checkbox">${acessorio.nome}</label><br/>
                </c:forEach>
            </c:forEach>
            </div>
            <div class="clear"></div>
        </div>
        <p class="botoes">
            <button type="submit" name="acao" value="salvar" class="primeiro-botao">Salvar</button>
            <button type="submit" name="acao" value="cancelar">Cancelar</button>
        </p>
    </fieldset>
</html:form>