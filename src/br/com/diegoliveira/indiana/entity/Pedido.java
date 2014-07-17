package br.com.diegoliveira.indiana.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe da entidade Pedido
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "pedido")
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name="pedido.por.carro",
        query="from Pedido m where m.modelo.carro.nome = :nome"
    )
})
public class Pedido implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_modelo")
	private Modelo modelo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pintura")
    private Pintura pintura;

    @Column(name = "preco", length=12)
    private double valor_total;

    @Column
    private Date dia;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "estado", length = 40)
    private String estado;

    @Column(name = "cep", length = 40)
    private String cep;

    @Column(name = "telefone", length = 40)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @ManyToMany
    @JoinTable(
        name = "pedido_acessorio",
        joinColumns = {@JoinColumn(name = "pedido_id")},
        inverseJoinColumns = {@JoinColumn(name = "acessorio_id")}
    )
    private List<Acessorio> acessorios;

    /**
     * Método que devolve nome da entidade
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que define nome da entidade
     * @param nome String
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que devolve id da entidade
     * @return int
     */
	public int getId() {
		return id;
	}

    /**
     * Método que define id da entidade
     * @param id int
     */
	public void setId(int id) {
		this.id = id;
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

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Pintura getPintura() {
        return pintura;
    }

    public void setPintura(Pintura pintura) {
        this.pintura = pintura;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * Método que transforma a entidade em uma String
     * @return String
     */
    @Override
	public String toString() {
		String result;
		result = "Cod: " + getId() + "\t";
		result += "Carro: " + getModelo().getCarro().getMarca().getNome() + " " + getModelo().getCarro().getNome() + "\t";
        result += "Modelo: " + getModelo().getNome() + "\t";
        result += "Valor Total: " + getValor_total() + "\t";
        result += "Data: " + getDia() + "\t";
        result += "Nome: " + getNome() + "\t";
        result += "Email: " + getEmail();

		return result;
	}
}
