package br.com.util;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class PagesUtil {
    public static void redirectPage(String page) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            String url = context.getExternalContext().getRequestContextPath();
            context.getExternalContext().redirect(url + "/pages/" + page + ".xhtml");
        } catch (Exception e) {
            System.out.println("Erro ao redirecionar\n Erro:" + e.getLocalizedMessage());
        }
    }
}
