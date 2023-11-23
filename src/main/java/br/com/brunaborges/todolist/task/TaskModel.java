package br.com.brunaborges.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//pagina de estrutura da tabela

  /**
     * 
     * id
     * usuário
     * descrição
     * título
     * data de inicio 
     * data de termino
     * prioridade
     * 
     */

@Data //get e set
@Entity(name = "tb_tasks")

public class TaskModel {

     //atributos
     @Id
     @GeneratedValue(generator = "UUID") //gerar o id automticamente 
     private UUID id;
     private String descricao;

     @Column(length = 50) //limitando o tamanho de caracteres do titulo 
     private String title;
     
     private LocalDateTime startAt;
     private LocalDateTime endAt;
     private String priority;

    @CreationTimestamp //automatico a data de criação 
     private LocalDateTime createdAt;

     private UUID idUser;
    
}
