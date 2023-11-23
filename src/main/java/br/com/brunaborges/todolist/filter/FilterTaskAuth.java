package br.com.brunaborges.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.brunaborges.todolist.user.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //td classe q quer q o string gerencie 
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if(servletPath.equals("/task")){

                //pegar a autenticação (user e senha)
                var authorization = request.getHeader("authorization");

                var authEncoded = authorization.substring("basic".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);

                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                /*System.out.println("authorization");
                System.out.println(username);
                System.out.println(password);*/

                //validar user
                var user = this.userRepository.findByUsername(username);
                if(user == null){
                    response.sendError(401);
                }
                else{
                    //validar senha 
                    var passwordVerify =  BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    request.setAttribute("idUser", user.getId());
                    if(passwordVerify.verified){
                        //segue viagem
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                }
            } else{
                filterChain.doFilter(request, response);
            }

    }

    
    
}
