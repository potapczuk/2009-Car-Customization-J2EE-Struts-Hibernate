package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import br.com.diegoliveira.indiana.DAO.ModeloDAO;
import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.entity.Modelo;
import br.com.diegoliveira.indiana.entity.Pedido;
import br.com.diegoliveira.indiana.entity.Pintura;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.util.List;

/**
 * Classe Form da entidade Marca
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class MonteForm extends AbstractForm {
	private String carro;
    private String modelo;
    private String pintura;
    private String[] acessorios;
    private String nome;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String email;
    private String etapa;
    private List marcas;
	private ArrayList<String> estados = null;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();

        if(etapa.equals("carro")){
            
        } else if (etapa.equals("modelo")) {
            if (stringVazia(getModelo())) {
                erros.add("Erros", new ActionMessage("erro.escolhaFaltando",
                        "Modelo"));
            }
        } else if (etapa.equals("pintura")) {
            if (stringVazia(getPintura())) {
                erros.add("Erros", new ActionMessage("erro.escolhaFaltando2",
                        "Pintura"));
            }
        } else if (etapa.equals("acessorios")) {

        } else if (etapa.equals("pedido")) {
            if (stringVazia(getNome())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "Nome"));
            }
            if (stringVazia(getCidade())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "Cidade"));
            }
            if (stringVazia(getEstado())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "Estado"));
            }
            if (stringVazia(getCep())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "CEP"));
            }
            if (stringVazia(getTelefone())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "Telefone"));
            } else if (telefoneErrado(getTelefone())) {
                erros.add("Erros", new ActionMessage(
					"erro.campoIncorreto", "Telefone",
					"(71) 3333-3333"));
            }
            if (stringVazia(getEmail())) {
                erros.add("Erros", new ActionMessage("erro.campoFaltando",
                        "E-mail"));
            }
        }
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        carro = "";
        modelo = "";
        pintura = "";
        acessorios = null;
        nome = "";
        cidade = "";
        estado = "";
        cep = "";
        telefone = "";
        email = "";
        marcas = null;
	}

	public Pedido getPedido() throws SQLException, ParseException {
		Pedido pedido = new Pedido();

        pedido.setDia(new Date());
		pedido.setNome(nome);
        pedido.setCidade(cidade);
        pedido.setEstado(estado);
        pedido.setCep(cep);
        pedido.setTelefone(telefone);
        pedido.setEmail(email);

        double valor_total = 0;

        ModeloDAO mdao = new ModeloDAO();
        Modelo modelobo = mdao.procuraById(Integer.valueOf(modelo));
        pedido.setModelo(modelobo);

        valor_total += modelobo.getPreco();

        PinturaDAO pdao = new PinturaDAO();
        Pintura pinturabo = pdao.procuraById(Integer.valueOf(pintura));
        pedido.setPintura(pinturabo);

        valor_total += pinturabo.getTipoDePintura().getPreco();

        AcessorioDAO adao = new AcessorioDAO();

        List<Acessorio> acessoriosList = new ArrayList<Acessorio>();
        Acessorio acessoriobo;
        if(acessorios != null){
            for(String acessorio : acessorios){
                acessoriobo = adao.procuraById(Integer.valueOf(acessorio));
                acessoriosList.add(acessoriobo);
                valor_total += acessoriobo.getPreco();
            }
        }
        pedido.setAcessorios(acessoriosList);

        pedido.setValor_total(valor_total);

		return pedido;
	}

    public String[] getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String[] acessorios) {
        this.acessorios = acessorios;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List getMarcas() {
        return marcas;
    }

    public void setMarcas(List marcas) {
        this.marcas = marcas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPintura() {
        return pintura;
    }

    public void setPintura(String pintura) {
        this.pintura = pintura;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}