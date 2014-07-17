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
 * Classe da entidade Pintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "pintura")
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name="pintura.por.tipo",
        query="from Pintura m where m.tipoDePintura = :tipoDePintura order by nome"
    )
})
public class Pintura implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 60)
	private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_tipo_de_pintura")
	private TipoDePintura tipoDePintura;

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

    public TipoDePintura getTipoDePintura() {
        return tipoDePintura;
    }

    public void setTipoDePintura(TipoDePintura tipoDePintura) {
        this.tipoDePintura = tipoDePintura;
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
        result += "Tipo de Pintura: " + getTipoDePintura();

		return result;
	}
}
