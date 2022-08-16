package com.Cliente.Cliente.controller;

import com.Cliente.Cliente.model.ClienteModel;
import com.Cliente.Cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/cliente")
    public List<ClienteModel> mostrarTodosClientes() {
        return clienteService.exibirTodosOsCliente();
    }

    @GetMapping(path = "/cliente/{codigo}")
    public Optional<ClienteModel> mostrarViaId(@PathVariable Long codigo) {
        return clienteService.exibirViaId(codigo);
    }

    //Metodo de validação para dados repetidos:
    @PostMapping(path = "/cliente")
    public ResponseEntity<Object> cadastrarNovoCliente(@RequestBody @Valid ClienteModel clienteModel) {
        if (clienteService.existsByCpf(clienteModel.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Cpf existente");

        } else if (clienteService.existsByEmail(clienteModel.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: E- mail existente");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarNovoCliente(clienteModel));
    }


    //@ResponseStatus(HttpStatus.CREATED)
    //public ClienteModel cadastrarNovoCliente(@RequestBody @Valid ClienteModel clienteModel) {
    // return clienteService.cadastrarNovoCliente(clienteModel);
    //}

    @PutMapping(path = "/cliente/{codigo}")
    public ClienteModel alterarViaId(@RequestBody @Valid ClienteModel clienteModel) {
        return clienteService.alterarCadastroDeCliente(clienteModel);
    }

    @DeleteMapping(path = "cliente/{codigo}")
    public void deletarViaId(@PathVariable Long codigo) {
        clienteService.deletarCliente(codigo);
    }


    //Método para aplicar as validações da classe model

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validacaoDeErros(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campoNome = ((FieldError) error).getField();
            String mensagemDeErro = error.getDefaultMessage();
            errors.put(campoNome, mensagemDeErro);
        });
        return errors;
    }

}
