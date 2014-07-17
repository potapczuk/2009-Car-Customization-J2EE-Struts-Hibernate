package br.com.diegoliveira.indiana.action;

import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.DAO.CarroDAO;
import br.com.diegoliveira.indiana.DAO.MarcaDAO;
import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.DAO.TipoDeAcessorioDAO;
import br.com.diegoliveira.indiana.DAO.TipoDePinturaDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.entity.Carro;
import br.com.diegoliveira.indiana.entity.Pintura;
import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import br.com.diegoliveira.indiana.entity.TipoDePintura;
import br.com.diegoliveira.indiana.form.CarroForm;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import java.util.ArrayList;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade Carro
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class CarroAction extends AbstractAction {

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

        CarroDAO dao = new CarroDAO();

        List<Carro> lista = dao.getLista();
        if (!lista.isEmpty()) {
            CarroForm dform = (CarroForm) form;
            dform.setCarros(lista);
            //request.setAttribute("carros", lista);
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

        // Pega lista de marcas
        MarcaDAO mdao = new MarcaDAO();
        List mlist = mdao.getLista();
        if(mlist == null || mlist.size() < 1){
            ActionMessages message = new ActionMessages();
            message.add("Erros",new ActionMessage("sem.cadastrada", "Marca"));
            saveErrors(request, message);
            return mapping.findForward("cancelar");
        }
        request.setAttribute("marcas", mlist);

        // Pega lista de pinturas dividido por tipo
        PinturaDAO pdao = new PinturaDAO();
        TipoDePinturaDAO tpdao = new TipoDePinturaDAO();

        List<TipoDePintura> tplist = tpdao.getLista();
        List<List> lplist = new ArrayList<List>();

        for(TipoDePintura tp : tplist){
            List<Pintura> plist = pdao.procuraByTipoDePintura(tp);
            if(plist != null && plist.size() > 0){
                lplist.add(plist);
            }
        }
        if(lplist.size() > 0){
            request.setAttribute("lpinturas", lplist);
        }

        // Pega lista de acessorios dividido por tipo
        AcessorioDAO adao = new AcessorioDAO();
        TipoDeAcessorioDAO tadao = new TipoDeAcessorioDAO();
        
        List<TipoDeAcessorio> talist = tadao.getLista();
        List<List> lalist = new ArrayList<List>();

        for(TipoDeAcessorio ta : talist){
            List<Acessorio> alist = adao.procuraByTipoDeAcessorio(ta);
            if(alist != null && alist.size() > 0){
                lalist.add(alist);
            }
        }
        if(lalist.size() > 0){
            request.setAttribute("lacessorios", lalist);
        }

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

        int id = convertID((String) request.getParameter("codCarro"));
        if (id > 0) {
            CarroDAO dao = new CarroDAO();
            Carro carro = dao.procuraById(id);

            ((CarroForm) form).setCarro(carro);
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

        int id = convertID((String) request.getParameter("codCarro"));
        if (id > 0) {
            CarroDAO dao = new CarroDAO();
            Carro carro = new Carro();
            carro.setId(id);

            dao.remove(carro);

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

            ActionErrors errors = ((CarroForm) form).validate(mapping, request);

			if (errors.isEmpty()) {
				CarroForm formulario = ((CarroForm) form);
				Carro carro = formulario.getCarro();

				CarroDAO dao = new CarroDAO();

				dao.salvaOuAtualiza(carro);

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