package com.Cliente.Cliente.repository;

import com.Cliente.Cliente.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

}
