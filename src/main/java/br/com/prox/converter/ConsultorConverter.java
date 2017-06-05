package br.com.prox.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.prox.controller.ConsultorBean;
import br.com.prox.controller.ProjetoBean;
import br.com.prox.model.Consultor;

@FacesConverter(value = "consultorConverter")
public class ConsultorConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String objId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{consultorBean}", ConsultorBean.class);

        ConsultorBean consultorBean = (ConsultorBean)vex.getValue(ctx.getELContext());
        for(Consultor con : consultorBean.getTodosConsultores()){
        	if (new Long(objId).equals(con.getId())){
                return con;
            }
        }
        
        return null; // consultorBean.getConsultor(Long.valueOf(objId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
    	if(obj != null && obj instanceof Consultor){
    		return ((Consultor)obj).getId().toString();
    	}
        return null;
    }
}
