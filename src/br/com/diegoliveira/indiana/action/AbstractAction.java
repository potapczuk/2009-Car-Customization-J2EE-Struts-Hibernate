package br.com.diegoliveira.indiana.action;


import br.com.diegoliveira.indiana.entity.Usuario;
import br.com.diegoliveira.indiana.utilidades.Constantes;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

/**
 * Classe Action abstrata com os métodos genéricos
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AbstractAction extends DispatchAction {
    // Permissao para toda a action
    protected String grupoPermission = Constantes.ANY;
    // Permissao para actions individuais
    protected Set<String> grupoActionPermission = new HashSet<String>();

    /**
     * Método chamado pelo Struts para chamar as acoes especificas das Actions
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    @Override
    protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
            String name) throws Exception{

        ActionForward forward = preOperation(mapping, form, request, response, name);

        if(forward != null) return forward;

        forward = super.dispatchMethod(mapping, form, request, response, name);

        forward = posOperation(forward, mapping, form, request, response, name);

        return forward;
    }

     /**
     * Método que faz as operacoes previas a action
     */
    protected ActionForward preOperation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
            String name) {
        if(!grupoPermission.equals(Constantes.ANY)){
            HttpSession session = request.getSession( );
            Usuario user = (Usuario) session.getAttribute(Constantes.USER_KEY);

            if(user == null) return (mapping.findForward("login"));

            if(grupoPermission.equals(Constantes.USUARIO) && !(user.getGrupo().equals(Constantes.USUARIO) ||
                                                              user.getGrupo().equals(Constantes.ADMIN)
                                                              )){
                ActionMessages msgs = new ActionMessages();
                msgs.add("Erros", new ActionMessage("permissao.invalida.usuario"));
                saveErrors(request, msgs);

                return (mapping.findForward("adm.erro"));
            }

            if(grupoPermission.equals(Constantes.ADMIN) && !(user.getGrupo().equals(Constantes.ADMIN))){
                ActionMessages msgs = new ActionMessages();
                msgs.add("Erros", new ActionMessage("permissao.invalida.usuario"));
                saveErrors(request, msgs);

                return (mapping.findForward("adm.erro"));
            }
        }

        return null;
    }
    
    /**
     * Método que faz as operacoes previas a action
     */
    protected ActionForward posOperation(ActionForward forward, ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
            String name){
        return forward;
    }

    /**
     * Método que converte um String contendo m id para um inteiro
     * @param id String
     * @return int
     */
	protected int convertID(String id) {
		int idNum = 0;
		if (id != null)// id should contain the id of the album to delete
			if (!id.equals(""))// then user is request to delete an album
			{
				// convert the String id to int id
				try {
					idNum = Integer.parseInt(id);
				} catch (NumberFormatException nfe) {
					// TODO Registrar mensagem de erro
					System.err.println(nfe.getLocalizedMessage());
				}
			}
		return idNum;
	}
}
