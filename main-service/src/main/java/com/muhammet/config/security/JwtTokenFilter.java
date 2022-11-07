package com.muhammet.config.security;

import com.muhammet.dto.request.FindByAuthidRequestDto;
import com.muhammet.dto.response.FindByAuthidResponseDto;
import com.muhammet.manager.IUserServiceManager;
import com.muhammet.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    IUserServiceManager userServiceManager;
    @Autowired
    JwtMyUser jwtMyUser;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
       if (authHeader!= null && authHeader.startsWith("Bearer ")){
           String token = authHeader.substring(7);
           Optional<Long> authid = jwtTokenManager.getByIdFromToken(token);
           if(authid.isPresent()){
               FindByAuthidResponseDto userProfile = userServiceManager.findByAuthid(FindByAuthidRequestDto.builder()
                               .authid(authid.get())
                       .build()).getBody();
               if(userProfile.getUserid()!=null){
                   UserDetails userDetails = jwtMyUser.loadByAuthid(userProfile,authid.get());
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(
                                   userDetails,null,
                                   userDetails.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
           }
        }
       filterChain.doFilter(request,response);
    }
}
