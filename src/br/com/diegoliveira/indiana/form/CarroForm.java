package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.AcessorioDAO;
import br.com.diegoliveira.indiana.DAO.MarcaDAO;
import br.com.diegoliveira.indiana.DAO.PinturaDAO;
import br.com.diegoliveira.indiana.entity.Acessorio;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Carro;
import br.com.diegoliveira.indiana.entity.Pintura;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Form da entidade Carro
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class CarroForm extends AbstractForm {
	private String id;
	private String nome;
    private String descricao;
    private String marca;
    private String[] pinturas;
    private String[] acessorios;
    private List carros;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();
		// verifica o nome
		if (stringVazia(getNome())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Nome do Carro"));
		}
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = "";
		nome = "";
        descricao = "";
        marca = null;
        carros = null;
	}

	public Carro getCarro() throws SQLException {
		Carro carro = new Carro();

		if (id != null && !id.equals(""))
			carro.setId(Integer.parseInt(id));
		carro.setNome(nome);
        carro.setDescricao(descricao);

        MarcaDAO mdao = new MarcaDAO();
        carro.setMarca(mdao.procuraById(Integer.valueOf(marca)));

        if(pinturas != null){
            PinturaDAO pdao = new PinturaDAO();
            List<Pintura> plist = new ArrayList<Pintura>();

            for(String pintura : pinturas){
                plist.add(pdao.procuraById(Integer.parseInt(pintura)));
            }

            carro.setPinturas(plist);
        }


        if(acessorios != null){
            AcessorioDAO adao = new AcessorioDAO();
            List<Acessorio> alist = new ArrayList<Acessorio>();

            for(String acessorio : acessorios){
                alist.add(adao.procuraById(Integer.parseInt(acessorio)));
            }

            carro.setAcessorios(alist);
        }

		return carro;
	}

	public void setCarro(Carro carro) {
        int i = 0;

		id = String.valueOf(carro.getId());
		nome = carro.getNome();
        descricao = carro.getDescricao();
        marca = Integer.toString(carro.getMarca().getId());

        i = 0;
        pinturas = new String[carro.getPinturas().size()];
        for(Pintura pintura : carro.getPinturas()){
            pinturas[i] = String.valueOf(pintura.getId());
            i++;
        }

        System.out.println(carro.getAcessorios().size());

        i = 0;
        acessorios = new String[carro.getAcessorios().size()];
        for(Acessorio acessorio : carro.getAcessorios()){
            acessorios[i] = String.valueOf(acessorio.getId());
            i++;
        }
	}

    public List getCarros() {
        return carros;
    }

    public void setCarros(List carros) {
        this.carros = carros;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String[] getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String[] acessorios) {
        this.acessorios = acessorios;
    }

    public String[] getPinturas() {
        return pinturas;
    }

    public void setPinturas(String[] pinturas) {
        this.pinturas = pinturas;
    }

}