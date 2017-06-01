package br.com.prox.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.prox.model.Consultor;
import br.com.prox.service.ConsultorService;

@FacesConverter(value = "consultorConverter")
public class ConsultorConverter implements Converter {
	
	private ConsultorService consultorService = new ConsultorService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.equals("")) {
			return null;
		}

		return consultorService.buscarId(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
			return null;
		}

		Consultor ramoAtividade = (Consultor) value;

		return ramoAtividade.getId().toString();
	}
}
