package br.com.getfood.pagamentos.controller;

import br.com.getfood.pagamentos.dto.PagamentoDTO;
import br.com.getfood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDTO> listarPagamentos(@PageableDefault(size = 10) Pageable paginacao) {
        return pagamentoService.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> obterPagamento(@PathVariable Long id) {
        PagamentoDTO pagamentoDTO = pagamentoService.obterPorId(id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> pagar(@RequestBody @Valid PagamentoDTO dto,
                                                        UriComponentsBuilder builder) {
        PagamentoDTO pagamento = pagamentoService.criarPagamento(dto);
        URI endereco = builder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
       // eu tenho que ter necessariamente uma URI para passar no created.
        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> alterarPagamento(@PathVariable @NotNull Long id,
                                                            @RequestBody @Valid PagamentoDTO dto) {
        PagamentoDTO atualizado = pagamentoService.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDTO> deletePagamento(@PathVariable Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

    //testando as portas dinamicas
    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta) {
        return String.format("Requisição respondida pela instância executando na porta %s", porta);

    }
}
