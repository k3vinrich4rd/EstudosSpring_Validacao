package com.Cliente.Cliente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "informacoesCliente")

public class ClienteModel {
//PRIMEIRO CRIAR A TABELA, DEPOIS EFETUAR AS VALIDAÇÕES
    //NÃO PODE PRECEDER AS VALIDAÇÕES!!!!
    //Coluna encima de NotBlank (Precedência)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Erro: campo não informado")
    private String nome;

    @Column(length = 100, nullable = false)
    @Email(message = "Erro: E-mail inválido")
    private String email;

    @Column(length = 100, nullable = false)
    @CPF(message = "Erro: Cpf inválido")
    private String cpf;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Erro: campo não informado")
    private String idade;


    @Column(length = 100, nullable = false)
    @NotBlank(message = "Erro: campo não informado")
    @Min(value = 1900, message = "Erro: a data de nascimento tem que ser superior a 1900")
    private String anoDeNascimento;
}
