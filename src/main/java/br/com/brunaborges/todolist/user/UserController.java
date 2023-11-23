package br.com.brunaborges.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * MODIFICADORES
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * TIPOS
     * String (texto)
     * Integer (int) numeros inteiros
     * Double (double) numeros 0.0000
     * Float (float) numeros 0.000
     * char (A C) um caractere
     * Date (data)
     * void - quando n tem um retorno do metodo
     */
    /**
     * 
     * Body - como ta trabalhando com o cadastro, as informações vem dentro do body da requisição 
     */

    @Autowired
    private IUserRepository userRepository;

    //metodos
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null){
            System.out.println("usuário já existe");
            //mensagem de erro
            //status code (status da requisição q define se foi sucesso ou erro)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário já existe");
        }

        //gerar uma senha criptografada
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); //transformar em um array

        userModel.setPassword(passwordHashred); //exibir a senha 
        
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
