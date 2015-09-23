package com.huivip.holu.webapp.filter;

import com.huivip.holu.util.AccessToken;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunlaihui on 8/14/15.
 */
public class RestAuthFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       /* userManager= (UserManager) ApplicationContextHolder.getBean("userManager");
        passwordEncoder= (PasswordEncoder) ApplicationContextHolder.getBean("passwordEncoder");*/
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        Base64 base64=new Base64();
        String access_token=request.getHeader("authorization");
        String requestPath=request.getRequestURI();
        if(requestPath.indexOf("userLogin.json")>0 || requestPath.indexOf("signup")>0 || requestPath.indexOf("/lasted/download.json")>0){
            filterChain.doFilter(request,response);
            return;
        }
        if(access_token==null || access_token.equalsIgnoreCase("")){
            response.setStatus(401);
            return;
        }
        if(!access_token.equalsIgnoreCase("login") && !access_token.equalsIgnoreCase("signup")){
            access_token=new String(base64.decode(access_token));
            String userID=access_token.substring(access_token.indexOf("$")+1,access_token.indexOf("%"));
            String token=access_token.substring(access_token.indexOf("%")+1);
            if(AccessToken.matches(userID, token)){
                filterChain.doFilter(request,response);
            }
            else {
                response.setStatus(401);
            }
        }
        else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
