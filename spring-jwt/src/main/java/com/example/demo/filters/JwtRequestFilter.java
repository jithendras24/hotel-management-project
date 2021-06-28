package com.example.demo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.MyUserDetailService;
import com.example.demo.utils.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private MyUserDetailService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		String jwt = null;
		String userName = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
			userName = jwtUtil.extractUserName(jwt);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				//In spring security this will happen by default
				UsernamePasswordAuthenticationToken authenticationToken 
										= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	
}
