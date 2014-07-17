package br.com.diegoliveira.indiana.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe da entidade Usuario
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
@Entity
@Table(name = "usuario")
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(
        name="usuario.verifica",
        query="from Usuario m where m.username = :username and m.password = :password"
    ),
    @org.hibernate.annotations.NamedQuery(
        name="usuario.getByUsername",
        query="from Usuario m where m.username = :username"
    )
})
public class Usuario implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "nome", nullable = false, length = 100)
	private String nome;

    @Column(name = "username", nullable = false, length = 60)
	private String username;

    @Column(name = "password", nullable = false, length = 80)
	private String password;

    @Column(name = "grupo", nullable = false, length = 30)
	private String grupo;

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        result += "Grupo: " + getGrupo();

		return result;
	}
}