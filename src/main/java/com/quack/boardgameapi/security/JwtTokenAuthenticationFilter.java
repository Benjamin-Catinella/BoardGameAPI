package com.quack.boardgameapi.security;

import com.quack.boardgameapi.entity.UserEntity;
import com.quack.boardgameapi.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

    @Autowired private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*
        TODO: Ajouter un contrôle de validité du token qui vérifie qu’il n’est pas expiré. Décider du
         traitement à effectuer dans ce cas là (génération d’une erreur, ajouter une trace dans les
         logs, et/ ou re-donner directement la main à Spring par exemple).
         */

        final String header = request.getHeader("Authorization");
        if(header == null){
            LOGGER.error("Header doesn't have Authorization");
            filterChain.doFilter(request, response);
            return;
        }else if (!header.startsWith("Bearer")){
            LOGGER.error("Authorization value didn't start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        // On “parse” le token en utilisant la même clé de signature qui sera utilisée pour générer le token à l’authentification (“secret”)
        final Claims claims;
        try {
            claims =
                    Jwts.parser().setSigningKey("canard".getBytes()).parseClaimsJws(token)
                            .getBody();

        }catch (ExpiredJwtException expiredJwtException){
            response.sendError(401, "Expired token");
            LOGGER.error("User tried to authentify with expired token", expiredJwtException);
            return;
        }
         // On récupère le nom de l’utilisateur indiqué dans l’objet
        final String username = claims.getSubject();
        // On récupère les informations de l’utilisateur grâce au repository
        UserEntity user = userRepository.getByUsername(username);
        final UserDetails userDetails = new User(user.getUsername(),user.getPassword(),user.getAuthorities());
        final UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );
        // Ajoute les informations de l’utilisateur
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        // Met à jour le contexte d’authentification
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Important : permet à Spring de continuer le traitement !
        filterChain.doFilter(request, response);

    }
}
