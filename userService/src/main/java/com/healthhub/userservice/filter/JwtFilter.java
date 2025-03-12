package com.healthhub.userservice.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;


@Component
public class JwtFilter extends GenericFilterBean {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("An exception occurred");
            }
        }

        final String token = authHeader.substring(7);

        System.out.println("token");
        System.out.println(token);
        
        
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();

        
        System.out.println("token");
        System.out.println(token);
        // Print the decoded claims
        System.out.println("Decoded JWT Claims: " + claims);
        // Alternatively, use a logger
        // Logger logger = LoggerFactory.getLogger(JwtFilter.class);
        // logger.info("Decoded JWT Claims: {}", claims);

        request.setAttribute("claims", claims);

        // System.out.println(servletRequest.getParameter("id"));
        // request.setAttribute("blog", servletRequest.getParameter("id"));
        filterChain.doFilter(request, response);
    }

    private Key key() {
    System.out.println(secret);
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

}
