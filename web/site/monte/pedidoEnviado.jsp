<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<h2>Monte seu carro</h2>

<h3>Seu pedido foi enviado com sucesso!</h3>

<div class="botao-grande">
     <html:link page="/Home.do">
        <span>Voltar para a Home</span>
    </html:link>
</div>

<div class="botao-grande">
     <html:link page="/Monte.do">
        <span>Montar outro carro</span>
    </html:link>
</div>