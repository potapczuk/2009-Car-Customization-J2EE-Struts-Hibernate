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
 * Classe da entidade Acessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "acessorio")
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name="acessorio.por.tipo",
        query="from Acessorio m where m.tipoDeAcessorio = :tipoDeAcessorio order by nome"
    )
})
public class Acessorio implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 60)
	private String nome;

    @Column(name = "descricao", length = 500)
	private String descricao;

    @Column(name = "preco", precision = 2, length = 12)
	private double preco;;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_tipo_de_acessorio")
	private TipoDeAcessorio tipoDeAcessorio;

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

    public TipoDeAcessorio getTipoDeAcessorio() {
        return tipoDeAcessorio;
    }

    public void setTipoDeAcessorio(TipoDeAcessorio tipoDeAcessorio) {
        this.tipoDeAcessorio = tipoDeAcessorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

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
        result += "Tipo de Acessorio: " + getTipoDeAcessorio();

		return result;
	}
}
