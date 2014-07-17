package br.com.diegoliveira.indiana.form;

import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Usuario;
import java.util.List;

/**
 * Classe Form da entidade Usuario
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class UsuarioForm extends AbstractForm {
	private String id;
	private String nome;
    private String username;
    private String password;
    private String grupo;
    private List usuarios;

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
        if (stringVazia(getUsername())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Username"));
		}
        if (stringVazia(getPassword())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Password"));
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
        username = "";
        password = "";
        grupo = "";
        usuarios = null;
	}

	public Usuario getUsuario() throws SQLException, ParseException {
		Usuario usuario = new Usuario();

		if (id != null && !id.equals(""))
			usuario.setId(Integer.parseInt(id));
		usuario.setNome(nome);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setGrupo(grupo);

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		id = String.valueOf(usuario.getId());
		nome = usuario.getNome();
        username = usuario.getUsername();
        password = usuario.getPassword();
        grupo = usuario.getGrupo();
	}

    public List getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}