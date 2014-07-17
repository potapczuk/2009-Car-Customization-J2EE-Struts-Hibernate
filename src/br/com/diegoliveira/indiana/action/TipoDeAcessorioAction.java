package br.com.diegoliveira.indiana.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.DAO.TipoDeAcessorioDAO;
import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import br.com.diegoliveira.indiana.form.TipoDeAcessorioForm;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade TipoDeAcessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class TipoDeAcessorioAction extends AbstractAction {

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

        TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();

        List<TipoDeAcessorio> lista = dao.getLista();
        
        if (lista != null && !lista.isEmpty()) {
            TipoDeAcessorioForm dform = (TipoDeAcessorioForm) form;
            dform.setTipoDeAcessorios(lista);
            //request.setAttribute("tipoDeTipoDeAcessorios", lista);
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

        // Recupera tipoDeTipoDeAcessorio
        int id = convertID((String) request.getParameter("codTipoDeAcessorio"));
        if (id > 0) {
            TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();
            TipoDeAcessorio tipoDeTipoDeAcessorio = dao.procuraById(id);

            ((TipoDeAcessorioForm) form).setTipoDeAcessorio(tipoDeTipoDeAcessorio);
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

        int id = convertID((String) request.getParameter("codTipoDeAcessorio"));
        if (id > 0) {
            TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();
            TipoDeAcessorio tipoDeTipoDeAcessorio = new TipoDeAcessorio();
            tipoDeTipoDeAcessorio.setId(id);

            dao.remove(tipoDeTipoDeAcessorio);

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

            ActionErrors errors = ((TipoDeAcessorioForm) form).validate(mapping, request);

			if (errors.isEmpty()) {
				TipoDeAcessorioForm formulario = ((TipoDeAcessorioForm) form);
				TipoDeAcessorio tipoDeTipoDeAcessorio = formulario.getTipoDeAcessorio();

				TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();

				dao.salvaOuAtualiza(tipoDeTipoDeAcessorio);

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