<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

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
<h2>Marcas</h2>

<html:errors property="Erros" suffix="erro.erro2" prefix="erro.erro-espacado" />

<html:errors property="Avisos" header="mensagem.header" footer="mensagem.footer" />

<div class="botoes direita">
    <html:link page="/Marca.do?acao=adicionar" styleClass="novo">
        <span>Novo</span>
    </html:link>
</div>

<table width="100%" border="0" cellpadding="2" cellspacing="1"
      class="display" id="listagem">
           <thead>
    <tr>
        <th scope="col">Nome</th>
        <th width="10%" scope="col">Editar</th>
        <th width="10%" scope="col">Remover</th>
        <th width="5%" scope="col">ID</th>
    </tr>
    </thead>
    <!-- for -->
           <tbody>
    <c:forEach var="marca" items="${MarcaForm.marcas}" varStatus="id">
        <tr>

            <td>${marca.nome}</td>
            <td align="center">
                <html:link page="/Marca.do?acao=editar&codMarca=${marca.id}">
                    <span>Editar</span>
                </html:link>
            </td>
            <td align="center">
                <html:link page="/Marca.do?acao=remover&codMarca=${marca.id}" styleClass="remover">
                    <span>Remover</span>
                </html:link>
            </td>
            <td align="center">${marca.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
