package br.com.diegoliveira.indiana.action;

import br.com.diegoliveira.indiana.DAO.UsuarioDAO;
import br.com.diegoliveira.indiana.entity.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.diegoliveira.indiana.form.LoginForm;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Classe Action da entidade Login
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class LoginAction extends AbstractAction {

    /**
     * Método chamado pelo Struts se nenhuma acao for definida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        return login(mapping, form, request, response);
    }

    /**
     * Método chamado pelo Struts se a acao login for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


        LoginForm formulario = ((LoginForm) form);
        String username = formulario.getUsername();
        String password = formulario.getPassword();
        if(username != null && !username.equals("") && password != null && !password.equals("")){
            return verificar(mapping, form, request, response);
        }

        HttpSession session = request.getSession( );
        Usuario user = (Usuario) session.getAttribute(Constantes.USER_KEY);

        if(user != null){
            return mapping.findForward("carros");
        }

        return mapping.findForward("login");
    }

    /**
     * Método chamado pelo Struts se a acao logoff for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward logoff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        request.getSession().setAttribute(Constantes.USER_KEY, null);

        ActionMessages message = new ActionMessages();
        message.add("Avisos",new ActionMessage("logoff.sucesso"));
        saveErrors(request, message);

        return mapping.findForward("login");
    }

    /**
     * Método chamado pelo Struts se a acao verificar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward verificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

            ActionErrors errors = ((LoginForm) form).validate(mapping, request);

			if (errors.isEmpty()) {

                LoginForm formulario = ((LoginForm) form);
				String username = formulario.getUsername();
                String password = formulario.getPassword();

                UsuarioDAO dao = new UsuarioDAO();
                if (!dao.verificaUsuario(username, password)) {
                    ActionMessages message = new ActionMessages();
                    message.add("Erros",new ActionMessage("erro.login.invalido"));
                    saveErrors(request, message);

                    return mapping.findForward("login");
                }

                Usuario user = dao.procuraByUsername(username);

                if(user == null){
                    ActionMessages message = new ActionMessages();
                    message.add("Erros",new ActionMessage("erro.login.invalido"));
                    saveErrors(request, message);

                    return mapping.findForward("login");
                }

                HttpSession session = request.getSession();
                session.setAttribute(Constantes.USER_KEY, user);

                request.setAttribute("acao", null);

				return mapping.findForward("carros");
			} else {
				saveErrors(request, errors);
				return mapping.findForward("login");
			}
    }

    /**
     * Método chamado pelo Struts se a acao cancelar for pedida
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionMessages msgs = new ActionMessages();
        msgs.add("Avisos", new ActionMessage("acao.cancelar"));

        saveErrors(request, msgs);

        return login(mapping, form, request, response);
    }
}