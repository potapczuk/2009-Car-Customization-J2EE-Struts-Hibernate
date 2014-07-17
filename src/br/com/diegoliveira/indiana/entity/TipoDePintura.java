package br.com.diegoliveira.indiana.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe da entidade TipoDePintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "tipoDePintura")
public class TipoDePintura implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 60)
	private String nome;

    @Column(name = "preco", length=12)
	private double preco;

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
        result += "Preço: " + getPreco();

		return result;
	}
}
