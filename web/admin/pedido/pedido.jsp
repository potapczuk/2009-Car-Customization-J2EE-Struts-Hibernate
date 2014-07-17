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
<h2>Pedidos</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro-espacado" />

<html:errors property="Avisos" header="mensagem.header" footer="mensagem.footer" />

<table width="100%" border="0" cellpadding="2" cellspacing="1"
      class="display" id="listagem">
    <thead>
        <tr>
            <th scope="col">Data</th>
            <th scope="col">Carro</th>
            <th scope="col">Valor</th>
            <th scope="col">Nome</th>
            <th width="10%" scope="col">Visualizar</th>
            <th width="10%" scope="col">Remover</th>
            <th width="5%" scope="col">ID</th>
        </tr>
    </thead>
    <!-- for -->
    <tbody>
        <c:forEach var="pedido" items="${PedidoForm.pedidos}" varStatus="id">
            <tr>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.dia}" /></td>
                <td>${pedido.modelo.carro.nome}</td>
                <td>R$ <fmt:formatNumber value="${pedido.valor_total}"
                        type="currency" minFractionDigits="2"
                        maxFractionDigits="2" currencySymbol="" /></td>
                <td>${pedido.nome}</td>
                <td align="center">
                    <html:link page="/Pedido.do?acao=visualizar&codPedido=${pedido.id}">
                        <span>Visualizar</span>
                    </html:link>
                </td>
                <td align="center">
                    <html:link page="/Pedido.do?acao=remover&codPedido=${pedido.id}" styleClass="remover">
                        <span>Remover</span>
                    </html:link>
                </td>
                <td align="center">${pedido.id}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
