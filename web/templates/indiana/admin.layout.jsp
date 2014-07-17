<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html
  PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" /></title>
        <link href="<%=request.getContextPath()%>/templates/indiana/css/estilo.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/templates/indiana/js/jquery.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/templates/indiana/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" type="image/x-icon" href="<%=request.getContextPath()%>/templates/indiana/img/favicon.ico">
    </head>

    <body>

        <div id="fundo-cima">
            <div id="container">
                <div id="cabecalho">
                    <tiles:insert attribute="header" />
                </div>

                <div class="clear"></div>
                <div id="menu-adm">
                    <tiles:insert attribute="menu" />
                </div>

                <div class="clear"></div>
                <div id="corpo">
                    <tiles:insert attribute="body" />
                </div>

                <div id="corpo-rodape">
                    <tiles:insert attribute="footer" />
                </div>
                <div class="clear"></div>
                <div id="rodape">
                    <div id="designedby">
                        <a href="#">Designed by: Diego Potapczuk</a>
                    </div>
                    <div id="irtopo">
                        <a href="#cabecalho">Ir para o topo</a>
                    </div>
                </div>
                <div class="clear"></div>
            <!-- end #container --></div>
        <!-- end #fundo-cima --></div>
        <div id="fundo-baixo"></div>
        
    </body>
</html>