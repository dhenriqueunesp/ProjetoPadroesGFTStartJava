package Dio.projeto.padroes.spring.controller;

import Dio.projeto.padroes.spring.model.Cliente;
import Dio.projeto.padroes.spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Iterable<Cliente>> buscarTodosPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(clienteService.buscarTodosPorCep(cep));
    }

    @GetMapping("/uf/{estado}")
    public ResponseEntity<Iterable<Cliente>> buscarTodosPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(clienteService.buscarTodosPorEstado(estado));
    }

    @GetMapping("/localidade/{cidade}")
    public ResponseEntity<Iterable<Cliente>> buscarTodosPorCidade(@PathVariable String cidade) {
        return ResponseEntity.ok(clienteService.buscarTodosPorCidade(cidade));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}