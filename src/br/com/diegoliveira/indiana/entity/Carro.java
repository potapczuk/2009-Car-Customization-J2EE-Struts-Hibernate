package br.com.diegoliveira.indiana.entity;

import java.io.Serializable;
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
 * Classe da entidade Carro
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "carro")
@org.hibernate.annotations.NamedQueries( {
    @org.hibernate.annotations.NamedQuery(
        name="carro.igual",
        query="from Carro m where m.nome=:nome order by nome"
        ),
    @org.hibernate.annotations.NamedQuery(
        name="carro.por.marca",
        query="from Carro m where m.marca=:marca order by nome"
        )
})
public class Carro implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 60)
	private String nome;

    @Column(name = "descricao", length = 500)
	private String descricao;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_marca")
	private Marca marca;

    @ManyToMany
    @JoinTable(
        name = "pintura_carro",
        joinColumns = {@JoinColumn(name = "carro_id")},
        inverseJoinColumns = {@JoinColumn(name = "pintura_id")}
    )
    private List<Pintura> pinturas;

    @ManyToMany
    @JoinTable(
        name = "acessorio_carro",
        joinColumns = {@JoinColumn(name = "carro_id")},
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
     * Método que devolve marca da entidade
     * @return Marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * Método que define marca da entidade
     * @param marca Marca
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }

    public List<Pintura> getPinturas() {
        return pinturas;
    }

    public void setPinturas(List<Pintura> pinturas) {
        this.pinturas = pinturas;
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
        result += "Marca: " + getMarca();

		return result;
	}
}
