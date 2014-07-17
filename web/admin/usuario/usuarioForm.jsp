<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<c:if test="${UsuarioForm.id == null || UsuarioForm.id < 1}">
	<h2>Novo Usuario</h2>
</c:if>
<c:if test="${UsuarioForm.id > 0}">
	<h2>Alterar Usuario</h2>
</c:if>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />
<html:form action="/Usuario" focus="nome" styleClass="formulario">
	<html:hidden property="id" />
    <fieldset>
        <legend><bean:message key="form.introducao"/></legend>
        <p>
            <label for="fnome"><span>Nome:</span></label>
            <html:text styleId="fnome" property="nome" maxlength="100" size="30"></html:text>
        </p>
        <p>
            <label for="fusername"><span>Username:</span></label>
            <html:text styleId="fusername" property="username" maxlength="60" size="30"></html:text>
        </p>
        <p>
            <label for="fpassword"><span>Password:</span></label>
            <html:password styleId="fpassword" property="password" maxlength="80" size="30"></html:password>
        </p>
        <p>
            <label for="fgrupo"><span>Grupo:</span></label>
            <html:select property="grupo" styleId="fgrupo">
                <html:options collection="grupos" property="nome" labelProperty="nome" />
            </html:select>
        </p>
        <p class="botoes">
            <button type="submit" name="acao" value="salvar" class="primeiro-botao">Salvar</button>
            <button type="submit" name="acao" value="cancelar">Cancelar</button>
        </p>
    </fieldset>
</html:form>