package br.com.diegoliveira.indiana.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.DAO.TipoDePinturaDAO;
import br.com.diegoliveira.indiana.entity.Pintura;
import br.com.diegoliveira.indiana.form.PinturaForm;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade Pintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class PinturaAction extends AbstractAction {

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

        PinturaDAO dao = new PinturaDAO();

        List<Pintura> lista = dao.getLista();
        
        if (lista != null && !lista.isEmpty()) {
            PinturaForm dform = (PinturaForm) form;
            dform.setPinturas(lista);
            //request.setAttribute("pinturas", lista);
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

        TipoDePinturaDAO dao = new TipoDePinturaDAO();
        List list = dao.getLista();
        if(list == null || list.size() < 1){
            ActionMessages message = new ActionMessages();
            message.add("Erros",new ActionMessage("sem.cadastrado", "Tipo de Pintura"));
            saveErrors(request, message);
            return mapping.findForward("cancelar");
        }
        request.setAttribute("tiposDePintura", list);

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

        // Recupera pintura
        int id = convertID((String) request.getParameter("codPintura"));
        if (id > 0) {
            PinturaDAO dao = new PinturaDAO();
            Pintura pintura = dao.procuraById(id);

            ((PinturaForm) form).setPintura(pintura);
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

        int id = convertID((String) request.getParameter("codPintura"));
        if (id > 0) {
            PinturaDAO dao = new PinturaDAO();
            Pintura pintura = new Pintura();
            pintura.setId(id);

            dao.remove(pintura);

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

            ActionErrors errors = ((PinturaForm) form).validate(mapping, request);

			if (errors.isEmpty()) {
				PinturaForm formulario = ((PinturaForm) form);
				Pintura pintura = formulario.getPintura();

				PinturaDAO dao = new PinturaDAO();

				dao.salvaOuAtualiza(pintura);

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