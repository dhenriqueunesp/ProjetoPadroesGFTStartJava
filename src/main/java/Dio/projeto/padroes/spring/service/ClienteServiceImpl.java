package Dio.projeto.padroes.spring.service;

import Dio.projeto.padroes.spring.model.Cliente;
import Dio.projeto.padroes.spring.model.ClienteRepository;
import Dio.projeto.padroes.spring.model.Endereco;
import Dio.projeto.padroes.spring.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Iterable<Cliente> buscarTodosPorCep(String cep) {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            if (!it.next().getEndereco().getCep().equals(cep)) {
                it.remove();
            }
        }
        return clientes;
    }

    @Override
    public Iterable<Cliente> buscarTodosPorEstado(String estado) {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            if (!it.next().getEndereco().getUf().equals(estado)) {
                it.remove();
            }
        }
        return clientes;
    }

    @Override
    public Iterable<Cliente> buscarTodosPorCidade(String cidade) {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            if (!it.next().getEndereco().getLocalidade().equals(cidade)) {
                it.remove();
            }
        }
        return clientes;
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
