package org.riskmanager.converters;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 14.10.12
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class PersonPasswordPropertyEditor extends PropertyEditorSupport {



    //hash the plain-text password
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text == null || text.length() == 0){
            setValue(null);
        }
        else {
            Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
            md5PasswordEncoder.setEncodeHashAsBase64(false);
            setValue(md5PasswordEncoder.encodePassword(text, null));
        }
    }
}
