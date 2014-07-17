package br.com.diegoliveira.indiana.action;

import br.com.diegoliveira.indiana.DAO.CarroDAO;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.DAO.ModeloDAO;
import br.com.diegoliveira.indiana.entity.Carro;
import br.com.diegoliveira.indiana.entity.Modelo;
import br.com.diegoliveira.indiana.form.ModeloForm;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade Modelo
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class ModeloAction extends AbstractAction {

    {
        grupoPermission = Constantes.USUARIO;
    }

    /**
     * Método chamado pelo Struts se nenhuma acao for definida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        return listar(mapping, form, request, response);
    }

    /**
     * Método chamado pelo Struts se a acao modelo for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward modelos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Recupera o carro definido
        Object codCarro = request.getParameter("codCarro");

        // Checa se o carro foi definido
        if(codCarro != null){
            // Busca o carro
            CarroDAO dao = new CarroDAO();

            int carro_id = Integer.valueOf((String)codCarro);
            Carro carro = dao.procuraById(carro_id);
            // Armazena na sessao
            request.getSession().setAttribute("carro", carro);
        } else {
            // Erro - carro nao definido
            ActionMessages msgs = new ActionMessages();
            msgs.add("Erros", new ActionMessage("carro.sem.definido"));
            saveErrors(request, msgs);
        }

        return listar(mapping, form, request, response);
    }

    /**
     * Método chamado pelo Struts se a acao listar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward listar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ModeloDAO dao = new ModeloDAO();

        Carro carro = (Carro) request.getSession().getAttribute("carro");

        if(carro == null){
            ActionMessages msgs = new ActionMessages();
            msgs.add("Erros", new ActionMessage("carro.sem.definido"));
            saveErrors(request, msgs);

            return mapping.findForward("carros");
        }

        List<Modelo> lista = dao.procuraByCarro(carro);
        
        if (lista != null && !lista.isEmpty()) {
            ModeloForm dform = (ModeloForm) form;
            dform.setModelos(lista);
            //request.setAttribute("modelos", lista);
        } else {
            ActionMessages msgs = new ActionMessages();
            msgs.add("Avisos", new ActionMessage("registros.vazio"));
            saveErrors(request, msgs);
        }

        request.setAttribute("carro", carro);

        return mapping.findForward("listar");
    }

    /**
     * Método chamado pelo Struts se a acao adicionar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward adicionar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Recupera o carro da sessao
        Carro carro = (Carro) request.getSession().getAttribute("carro");

        // Checa se o carro existe
        if(carro == null){
            ActionMessages message = new ActionMessages();
            message.add("Erros",new ActionMessage("carro.sem.definido"));
            saveErrors(request, message);

            return listar(mapping, form, request, response);
        }
        // Coloca o carro no request
        request.setAttribute("carro", carro);

        return mapping.findForward("adicionar");
    }

    /**
     * Método chamado pelo Struts se a acao editar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Recupera modelo
        int id = convertID((String) request.getParameter("codModelo"));
        if (id > 0) {
            ModeloDAO dao = new ModeloDAO();
            Modelo modelo = dao.procuraById(id);

            ((ModeloForm) form).setModelo(modelo);
        }

        return adicionar(mapping, form, request, response);
        //return mapping.findForward("editar");
    }

    /**
     * Método chamado pelo Struts se a acao remover for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward remover(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        int id = convertID((String) request.getParameter("codModelo"));
        if (id > 0) {
            ModeloDAO dao = new ModeloDAO();
            Modelo modelo = new Modelo();
            modelo.setId(id);

            dao.remove(modelo);

            ActionMessages message = new ActionMessages();

            message.add("Avisos",new ActionMessage("remover.sucesso"));

            saveErrors(request, message);
        }

        return listar(mapping, form, request, response);
    }

    /**
     * Método chamado pelo Struts se a acao salvar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

            ActionErrors errors = ((ModeloForm) form).validate(mapping, request);

			if (errors.isEmpty()) {
				ModeloForm formulario = ((ModeloForm) form);
				Modelo modelo = formulario.getModelo();

				ModeloDAO dao = new ModeloDAO();

				dao.salvaOuAtualiza(modelo);

                ActionMessages msgs = new ActionMessages();
                msgs.add("Avisos", new ActionMessage("salvar.sucesso"));
                
                saveErrors(request, msgs);

				return listar(mapping, form, request, response);
			} else {
				saveErrors(request, errors);
				return editar(mapping, form, request, response);
			}
    }

    /**
     * Método chamado pelo Struts se a acao cancelar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionMessages msgs = new ActionMessages();
        msgs.add("Avisos", new ActionMessage("acao.cancelar"));

        saveErrors(request, msgs);

        return listar(mapping, form, request, response);
    }
}