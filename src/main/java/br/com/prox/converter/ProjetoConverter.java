package br.com.prox.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.prox.controller.ProjetoBean;
import br.com.prox.model.Projeto;

@FacesConverter(value = "projetoConverter")
public class ProjetoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String objId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{projetoBean}", ProjetoBean.class);

        ProjetoBean projetoBean = (ProjetoBean)vex.getValue(ctx.getELContext());
        return projetoBean.getProjeto(Long.valueOf(objId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
    	if(obj != null && obj instanceof Projeto){
    		return ((Projeto)obj).getId().toString();
    	}
        return null;
    }

	
}
