package Dio.projeto.padroes.spring.service;

import Dio.projeto.padroes.spring.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);

    Iterable<Cliente> buscarTodosPorCep(String cep);

    Iterable<Cliente> buscarTodosPorEstado(String estado);

    Iterable<Cliente> buscarTodosPorCidade(String cidade);
}
