package br.com.brunaborges.todolist.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

//modelo dentro da aplicação 
//<> - a classe recebe um generator 
public interface IUserRepository extends JpaRepository<UserModel, UUID>{

    UserModel findByUsername(String username); //fazer um select uscando essa coluna 
}
