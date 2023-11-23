//componente que é utilizada p ser a camada entre a requisição e as demais camadas
//1 camada de acesso p receber a requisição do usuario 

package br.com.brunaborges.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

//@Controller - flexibilidade maior de retornar objetos, paginas, templates
@RestController //quando ta construindo uma api
@RequestMapping("/primeiraRota") //responsavel por estruturar a rota
// http://localhost:8080/primeiraRota - toda vez q colocar a url da aplicação /primeiraRota, ele entra nessa controller


public class MinhaPrimeiraController {
    
/**
 * METODOS DE ACESSO DO HTTP
 * GET - buscar uma informação
 * POST - adicionar uma informação/dado
 * PUT - alterar um dado/informação
 * DELETE - remover um dado
 * PATCH - alterar somente uma parte da informação/dado
 */

    //metodo (funcionalidade) de uma classe
    @GetMapping("/primeiroMetodo")
    public String primeiraMensagem(){
        return "funcionou";
    }
}
