package com.sheryians.major.configuration;


import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;
import org.apache.catalina.filters.ExpiresFilter;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;




    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();




    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
    String email=token.getPrincipal().getAttributes().get("email").toString();
    if(userRepository.findUserByEmail(email).isPresent() ){

    }else{
        User user =new User();
        user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());//firstName
        user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());//LastName
        user.setEmail(email);
        List<Role> roles=new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
    }
    redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");

    }




}
