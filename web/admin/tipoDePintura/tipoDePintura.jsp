<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script language="javascript" type="text/javascript">
    <!--
    $(document).ready(function() {
        $(".remover").click(function(ev){
            if (!confirm("Deseja realmente apagar este registro?")){
                ev.preventDefault();
            }
            this.blur();
        });

        $('#listagem').dataTable( {
            "aaSorting": [[ 0, "asc" ]],
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Não foram encontrados resultados",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "oPaginate": {
                    "sFirst":    "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext":     "Seguinte",
                    "sLast":     "Último"
                }
            }
        });
    } );
    -->
</script>
<h2>Tipo de Pinturas</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro-espacado" />

<html:errors property="Avisos" header="mensagem.header" footer="mensagem.footer" />

<div class="botoes direita">
    <html:link page="/TipoDePintura.do?acao=adicionar" styleClass="novo">
        <span>Novo</span>
    </html:link>
    <html:link page="/Pintura.do" styleClass="novo">
        <span>Voltar para pinturas</span>
    </html:link>
</div>

<table width="100%" border="0" cellpadding="2" cellspacing="1"
      class="display" id="listagem">
    <thead>
        <tr>
            <th scope="col">Nome</th>
            <th width="15%" scope="col">Preço</th>
            <th width="10%" scope="col">Editar</th>
            <th width="10%" scope="col">Remover</th>
            <th width="5%" scope="col">ID</th>
        </tr>
    </thead>
    <!-- for -->
    <tbody>
        <c:forEach var="tipoDePintura" items="${TipoDePinturaForm.tipoDePinturas}" varStatus="id">
            <tr>
                <td>${tipoDePintura.nome}</td>
                <td>R$ <fmt:formatNumber value="${tipoDePintura.preco}"
            type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></td>
                <td align="center">
                    <html:link page="/TipoDePintura.do?acao=editar&codTipoDePintura=${tipoDePintura.id}">
                        <span>Editar</span>
                    </html:link>
                </td>
                <td align="center">
                    <html:link page="/TipoDePintura.do?acao=remover&codTipoDePintura=${tipoDePintura.id}" styleClass="remover">
                        <span>Remover</span>
                    </html:link>
                </td>
                <td align="center">${tipoDePintura.id}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
