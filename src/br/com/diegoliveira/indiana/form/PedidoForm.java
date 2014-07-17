package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import br.com.diegoliveira.indiana.DAO.ModeloDAO;
import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Form da entidade Pedido
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class PedidoForm extends AbstractForm {
	private String id;
	private String modelo;
    private String pintura;
    private String valor_total;
    private String dia;
    private String nome;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String email;
    private List<String> acessorios;
    
    private Pedido pedidoEntity;
    private List<Pedido> pedidos;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();
		// verifica o nome
		if (stringVazia(getNome())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Nome"));
		}
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = "";
        modelo = "";
        pintura = "";
        valor_total = "";
        dia = "";
        nome = "";
        cidade = "";
        estado = "";
        cep = "";
        telefone = "";
        email = "";
        acessorios = null;
        pedidos = null;
	}

	public Pedido getPedido() throws SQLException, ParseException {
		Pedido pedido = new Pedido();

		if (id != null && !id.equals(""))
			pedido.setId(Integer.parseInt(id));

        pedido.setValor_total(Double.parseDouble(converteCurrencyParaDouble(valor_total)));
        pedido.setDia(converterStringParaDate(dia, FORMATODATA));
		pedido.setNome(nome);
        pedido.setCidade(cidade);
        pedido.setEstado(estado);
        pedido.setCep(cep);
        pedido.setTelefone(telefone);
        pedido.setEmail(email);

        ModeloDAO mdao = new ModeloDAO();
        pedido.setModelo(mdao.procuraById(Integer.valueOf(modelo)));

        PinturaDAO pdao = new PinturaDAO();
        pedido.setPintura(pdao.procuraById(Integer.valueOf(pintura)));

        AcessorioDAO adao = new AcessorioDAO();

        List<Acessorio> acessoriosList = new ArrayList<Acessorio>();
        for(String acessorio : acessorios){
            acessoriosList.add(adao.procuraById(Integer.valueOf(acessorio)));
        }
        pedido.setAcessorios(acessoriosList);

		return pedido;
	}

	public void setPedido(Pedido pedido) {
		id = String.valueOf(pedido.getId());
        modelo = String.valueOf(pedido.getModelo().getId());
        pintura = String.valueOf(pedido.getPintura().getId());
        valor_total = converteDoubleParaCurrency(String.valueOf(pedido.getValor_total()));
        dia = converterDateParaString(pedido.getDia(), FORMATODATA);
        nome = pedido.getNome();
        cidade = pedido.getCidade();
        estado = pedido.getEstado();
        cep = pedido.getCep();
        telefone = pedido.getTelefone();
        email = pedido.getEmail();

        acessorios = new ArrayList<String>();
        
        for(Acessorio acessorio : pedido.getAcessorios()){
            acessorios.add(String.valueOf(acessorio.getId()));
        }
	}

    public Pedido getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(Pedido pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public List<String> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<String> acessorios) {
        this.acessorios = acessorios;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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

    public String getValor_total() {
        return valor_total;
    }

    public void setValor_total(String valor_total) {
        this.valor_total = valor_total;
    }


}