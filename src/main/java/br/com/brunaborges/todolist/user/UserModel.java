package br.com.brunaborges.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data //- coloca todos os getters e setters 
//@Getter - so coloca o getter
//@Setter - so coloca o setter 
@Entity(name = "tb_users") //objeto q vai representar uma tabela 

public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;  //chave primaria 

    @Column(unique = true) //username com atributo unico, se tiver ja existente da um erro 
    private String username;
    private String name;
    private String password;

    @CreationTimestamp //data automatica
    private LocalDateTime createdAt;

    //getters(buscar informações que ta dentro dele)
    //setters(inserir um valor p um atributo privado de uma classe)
}
