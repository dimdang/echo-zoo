package org.cool.zoo.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Dang Dim
 * Date     : 08-Jan-18, 1:00 PM
 * Email    : d.dim@gl-f.com
 */

public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotActivatedException(String msg) {
        super(msg);
    }
}
