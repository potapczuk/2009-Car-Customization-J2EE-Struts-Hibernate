package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 * Classe Form da entidade Login
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class LoginForm extends AbstractForm {
	private String username;
    private String password;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();
		// verifica o nome
		if (stringVazia(getUsername())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Nome de usuário"));
		}
        if (stringVazia(getPassword())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Senha"));
		}
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		username = "";
        password = "";
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
}