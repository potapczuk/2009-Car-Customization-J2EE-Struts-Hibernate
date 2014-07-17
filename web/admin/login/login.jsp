<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro" />

<html:errors property="Avisos" header="mensagem.header" footer="mensagem.footer" />

<html:form action="/Login" focus="username" styleClass="formulario login">
    <html:hidden name="logar" value="logar" property="logar" />
    <fieldset>
        <legend>Por favor informe seus dados de login abaixo</legend>
        <p>
            <label for="fusername"><span>Nome de usuário:</span></label>
            <html:text styleId="fusername" property="username" maxlength="100" size="22"></html:text>
        </p>
        <p> 
            <label for="fpassword"><span>Senha:</span></label>
            <html:password styleId="fpassword" property="password" maxlength="100" size="22"></html:password>
        </p>
        <p class="botoes">
            <button type="submit" class="primeiro-botao">Login</button>
        </p>
    </fieldset>
</html:form>