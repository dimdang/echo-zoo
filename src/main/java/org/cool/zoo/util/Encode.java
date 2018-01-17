package org.cool.zoo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Dang Dim
 * Date     : 13-Jan-18, 2:19 PM
 * Email    : d.dim@gl-f.com
 */

public class Encode {

    /*public static void main(String[]args){
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }*/

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
