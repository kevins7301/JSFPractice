package at.jsfpractice.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class TestController {

    private Long longValue;

    public Validator getLongValidator() {
        return new LongValidator();
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public static class LongValidator implements Validator 
    {
        public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
        {
        	System.err.println("value==="+value);
            if (((Long)value) < 10) 
            {
            	System.err.println("value2==="+((Long)value));
                throw new ValidatorException(new FacesMessage("error"));
            }
        }
    }

}
