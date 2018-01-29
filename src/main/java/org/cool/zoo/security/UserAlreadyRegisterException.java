package org.cool.zoo.security;

import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;

/**
 * Created by Dang Dim
 * Date     : 29-Jan-18, 9:08 AM
 * Email    : d.dim@gl-f.com
 */

public class UserAlreadyRegisterException extends ClientAlreadyExistsException {

    public UserAlreadyRegisterException(String msg) {
        super(msg);
    }

    public UserAlreadyRegisterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
