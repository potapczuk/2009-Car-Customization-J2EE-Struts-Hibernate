package br.com.diegoliveira.indiana.action;


import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import br.com.diegoliveira.indiana.DAO.CarroDAO;
import br.com.diegoliveira.indiana.DAO.MarcaDAO;
import br.com.diegoliveira.indiana.DAO.ModeloDAO;
import br.com.diegoliveira.indiana.DAO.PedidoDAO;
import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.DAO.TipoDeAcessorioDAO;
import br.com.diegoliveira.indiana.DAO.TipoDePinturaDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.entity.Carro;
import br.com.diegoliveira.indiana.entity.Marca;
import br.com.diegoliveira.indiana.entity.Modelo;
import br.com.diegoliveira.indiana.entity.Pedido;
import br.com.diegoliveira.indiana.entity.Pintura;
import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import br.com.diegoliveira.indiana.entity.TipoDePintura;
import br.com.diegoliveira.indiana.form.MonteForm;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Classe Action da entidade Marca
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class MonteAction extends AbstractAction {


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

        return escolherCarro(mapping, form, request, response);
    }

    /**
     * Método chamado pelo Struts se a acao escolherCarro for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward escolherCarro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Pega lista de pinturas dividido por tipo
        CarroDAO cdao = new CarroDAO();
        MarcaDAO mdao = new MarcaDAO();

        List<Marca> mlist = mdao.getLista();
        List<List> lclist = new ArrayList<List>();

        for(Marca marca : mlist){
            List<Carro> clist = cdao.procuraByMarca(marca);
            if(clist != null && clist.size() > 0){
                lclist.add(clist);
            }
        }
        if(lclist.size() > 0){
            request.setAttribute("lcarros", lclist);
        }

        return mapping.findForward("escolherCarro");
    }

    /**
     * Método chamado pelo Struts se a acao escolherModelo for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward escolherModelo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        String carroF = ((MonteForm) form).getCarro();

        CarroDAO cdao = new CarroDAO();
        Carro carro = cdao.procuraById(Integer.parseInt(carroF));

        ModeloDAO dao = new ModeloDAO();
        List<Modelo> list = dao.procuraByCarro(carro);

        request.setAttribute("carro", carro);
        request.setAttribute("modelos", list);

        return mapping.findForward("escolherModelo");
    }

    /**
     * Método chamado pelo Struts se a acao escolherPintura for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward escolherPintura(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        MonteForm formM = (MonteForm) form;

        if(formM.getEtapa().equals("modelo")){
            ActionErrors errors = ((MonteForm) form).validate(mapping, request);

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                return escolherModelo(mapping, form, request, response);
            }
        }

        ModeloDAO mdao = new ModeloDAO();
        Modelo modelo = mdao.procuraById(Integer.parseInt(formM.getModelo()));
        
        Carro carro = modelo.getCarro();

        // Pega lista de pinturas dividido por tipo e carro
        CarroDAO cdao = new CarroDAO();
        TipoDePinturaDAO tpdao = new TipoDePinturaDAO();

        List<TipoDePintura> tplist = tpdao.getLista();
        List<List> lplist = new ArrayList<List>();

        for(TipoDePintura tp : tplist){
            List<Pintura> plist = cdao.pinturasPorTipoDePintura(tp, carro);
            if(plist != null && plist.size() > 0){
                lplist.add(plist);
            }
        }

        request.setAttribute("carro", carro);
        request.setAttribute("modelo", modelo);
        request.setAttribute("lpinturas", lplist);

        return mapping.findForward("escolherPintura");
    }


    /**
     * Método chamado pelo Struts se a acao escolherAcessorios for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward escolherAcessorios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        MonteForm formM = (MonteForm) form;

        if(formM.getEtapa().equals("pintura")){
            ActionErrors errors = ((MonteForm) form).validate(mapping, request);

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                return escolherPintura(mapping, form, request, response);
            }
        }

        ModeloDAO mdao = new ModeloDAO();
        Modelo modelo = mdao.procuraById(Integer.parseInt(formM.getModelo()));

        Carro carro = modelo.getCarro();

        PinturaDAO pdao = new PinturaDAO();
        Pintura pintura = pdao.procuraById(Integer.parseInt(formM.getPintura()));

        // Pega lista de acessorios dividido por tipo e carro
        CarroDAO cdao = new CarroDAO();
        TipoDeAcessorioDAO tpdao = new TipoDeAcessorioDAO();

        List<TipoDeAcessorio> tplist = tpdao.getLista();
        List<List> lplist = new ArrayList<List>();

        for(TipoDeAcessorio tp : tplist){
            List<Acessorio> plist = cdao.acessoriosPorTipoDeAcessorio(tp, carro);
            if(plist != null && plist.size() > 0){
                lplist.add(plist);
            }
        }

        request.setAttribute("carro", carro);
        request.setAttribute("modelo", modelo);
        request.setAttribute("pintura", pintura);
        request.setAttribute("lacessorios", lplist);

        return mapping.findForward("escolherAcessorios");
    }

    /**
     * Método chamado pelo Struts se a acao enviarPedido for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward enviarPedido(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        MonteForm formM = (MonteForm) form;

        if(formM.getEtapa().equals("acessorios")){
            ActionErrors errors = ((MonteForm) form).validate(mapping, request);

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                return escolherAcessorios(mapping, form, request, response);
            }
        }

        ModeloDAO mdao = new ModeloDAO();
        Modelo modelo = mdao.procuraById(Integer.parseInt(formM.getModelo()));

        Carro carro = modelo.getCarro();

        PinturaDAO pdao = new PinturaDAO();
        Pintura pintura = pdao.procuraById(Integer.parseInt(formM.getPintura()));

        AcessorioDAO adao = new AcessorioDAO();
        List<Acessorio> acessorios = new ArrayList<Acessorio>();

        String acs[] = null;

        if(formM.getAcessorios() == null){
            acs = (String[])request.getSession().getAttribute("p.acessorios");
        } else {
            acs = formM.getAcessorios();
            request.getSession().setAttribute("p.acessorios", acs);
        }
        if(acs != null){
            for(String acessorio : acs){
                acessorios.add(adao.procuraById(Integer.parseInt(acessorio)));
            }
        }

        double total;
        total = modelo.getPreco();
        total += pintura.getTipoDePintura().getPreco();
        for(Acessorio ac : acessorios){
            total += ac.getPreco();
        }

        PedidoDAO ppdao = new PedidoDAO();

        ((MonteForm) form).setEstados((ArrayList<String>) ppdao.getEstados());

        request.setAttribute("carro", carro);
        request.setAttribute("modelo", modelo);
        request.setAttribute("pintura", pintura);
        request.setAttribute("lacessorios", acessorios);
        request.setAttribute("total", total);

        return mapping.findForward("enviarPedido");
    }

    /**
     * Método chamado pelo Struts se a acao enviar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward enviar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        MonteForm formM = (MonteForm) form;

        if(formM.getEtapa().equals("pedido")){
            ActionErrors errors = ((MonteForm) form).validate(mapping, request);

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
                return enviarPedido(mapping, form, request, response);
            }
        }

        PedidoDAO pdao = new PedidoDAO();
        formM.setAcessorios((String[])request.getSession().getAttribute("p.acessorios"));
        request.getSession().removeAttribute("p.acessorios");
        Pedido pedido = formM.getPedido();
        pdao.salva(pedido);

        return mapping.findForward("pedidoEnviado");
    }
}