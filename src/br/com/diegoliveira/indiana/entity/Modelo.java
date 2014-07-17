package br.com.diegoliveira.indiana.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe da entidade Modelo
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "modelo")
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name="modelo.carro",
        query="from Modelo m where m.carro = :carro"
    )
})
public class Modelo implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 60)
	private String nome;

    @Column(name = "descricao", length = 500)
	private String descricao;

    @Column(name = "preco", precision = 2, length = 12)
	private double preco;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_carro")
	private Carro carro;

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

    /**
     * Método que devolve descricao da entidade
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que define descricao da entidade
     * @param descricao String
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método que devolve carro da entidade
     * @return Carro
     */
    public Carro getCarro() {
        return carro;
    }

    /**
     * Método que define carro da entidade
     * @param carro Carro
     */
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    /**
     * Método que devolve preco da entidade
     * @return double
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método que define preco da entidade
     * @param preco double
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }



    /**
     * Método que transforma a entidade em uma String
     * @return String
     */
    @Override
	public String toString() {
		String result;
		result = "Cod: " + getId() + "\t";
		result += "Nome: " + getNome() + "\t";
        result += "Descrição: " + getDescricao() + "\t";
        result += "Preço: " + getPreco() + "\t";
        result += "Carro: " + getCarro();

		return result;
	}
}
