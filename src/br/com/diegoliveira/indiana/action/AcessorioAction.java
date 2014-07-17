package br.com.diegoliveira.indiana.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import br.com.diegoliveira.indiana.DAO.TipoDeAcessorioDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.form.AcessorioForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade Acessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AcessorioAction extends AbstractAction {

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

        AcessorioDAO dao = new AcessorioDAO();

        List<Acessorio> lista = dao.getLista();
        
        if (lista != null && !lista.isEmpty()) {
            AcessorioForm dform = (AcessorioForm) form;
            dform.setAcessorios(lista);
            //request.setAttribute("acessorios", lista);
        } else {
            ActionMessages msgs = new ActionMessages();
            msgs.add("Avisos", new ActionMessage("registros.vazio"));
            saveErrors(request, msgs);
        }

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

        TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();
        List list = dao.getLista();
        if(list == null || list.size() < 1){
            ActionMessages message = new ActionMessages();
            message.add("Erros",new ActionMessage("sem.cadastrado", "Tipo de Acessorio"));
            saveErrors(request, message);
            return mapping.findForward("cancelar");
        }
        request.setAttribute("tiposDeAcessorio", list);

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

        // Recupera acessorio
        int id = convertID((String) request.getParameter("codAcessorio"));
        if (id > 0) {
            AcessorioDAO dao = new AcessorioDAO();
            Acessorio acessorio = dao.procuraById(id);

            ((AcessorioForm) form).setAcessorio(acessorio);
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

        int id = convertID((String) request.getParameter("codAcessorio"));
        if (id > 0) {
            AcessorioDAO dao = new AcessorioDAO();
            Acessorio acessorio = new Acessorio();
            acessorio.setId(id);

            dao.remove(acessorio);

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

            ActionErrors errors = ((AcessorioForm) form).validate(mapping, request);

			if (errors.isEmpty()) {
				AcessorioForm formulario = ((AcessorioForm) form);
				Acessorio acessorio = formulario.getAcessorio();

				AcessorioDAO dao = new AcessorioDAO();

				dao.salvaOuAtualiza(acessorio);

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