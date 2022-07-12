package com.example.Security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Security.Service.UtilDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils; 

    @Autowired
    private UtilDetailsServiceImpl utilDetailsServiceImpl;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String auth = request.getHeader("Authorization");
                String token = null;
                String username = null;

    
                if(auth!=null && auth.startsWith("bearer")){
                    token=auth.substring(7);
                    username=jwtUtils.extractUsername(token);
                }
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails details = userDetailsService.loadUserByUsername(username);
                    if(jwtUtils.validateToken(token, details)){
                        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,null, details.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
                filterChain.doFilter(request, response);
    }
}
