package com.resotrekk.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.resotrekk.validator.NumberValidator")
public class NumberValidator implements Validator{
	
	// *********************************************************************************************
	private static final String Number_PATTERN = "[0-9]*";

	private Pattern pattern;
	private Matcher matcher;

	// *********************************************************************************************

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("validation failed.", 
						"Invalid  format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
	}
	
	// *********************************************************************************************

	public NumberValidator() {
		pattern = Pattern.compile(Number_PATTERN);
	}

}
