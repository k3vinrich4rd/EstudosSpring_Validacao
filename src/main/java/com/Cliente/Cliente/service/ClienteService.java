package com.Cliente.Cliente.service;

import com.Cliente.Cliente.model.ClienteModel;
import com.Cliente.Cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> exibirTodosOsCliente() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> exibirViaId(Long codigo) {
        return clienteRepository.findById(codigo);

    }

    public ClienteModel cadastrarNovoCliente(ClienteModel clienteModel) {
        clienteModel.getId();
        clienteModel.getNome();
        clienteModel.getEmail();
        clienteModel.getCpf();
        clienteModel.getIdade();
        clienteModel.getAnoDeNascimento();

        return clienteRepository.save(clienteModel);
    }

    public ClienteModel alterarCadastroDeCliente(ClienteModel clienteModel) {
        clienteModel.getId();
        clienteModel.getNome();
        clienteModel.getEmail();
        clienteModel.getCpf();
        clienteModel.getIdade();
        clienteModel.getAnoDeNascimento();

        return clienteRepository.save(clienteModel);
    }

    public void deletarCliente(Long codigo) {
        clienteRepository.deleteById(codigo);
    }

    //Validar informações repetidas:
    public boolean existsByCpf(String cpf){
        return clienteRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email){
        return clienteRepository.existsByEmail(email);
    }


}

