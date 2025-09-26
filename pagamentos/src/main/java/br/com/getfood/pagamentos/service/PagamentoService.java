package br.com.getfood.pagamentos.service;

import br.com.getfood.pagamentos.dto.PagamentoDTO;
import br.com.getfood.pagamentos.http.PedidoClient;
import br.com.getfood.pagamentos.model.Pagamento;
import br.com.getfood.pagamentos.model.Status;
import br.com.getfood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoClient pedidoClient;

    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(pagamento -> modelMapper.map(pagamento, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long Id) {
        Pagamento pagamento = repository
                .findById(Id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO criarPagamento(PagamentoDTO dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto) {
        Pagamento pagamentoExistente = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        modelMapper.map(dto, pagamentoExistente);
        pagamentoExistente.setId(id);

        Pagamento pagamentoAtualizado = repository.save(pagamentoExistente);

        return modelMapper.map(pagamentoAtualizado, PagamentoDTO.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

    public void confirmarPagamento (Long id) {
        Pagamento pagamento = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pagamento não encontrado por esse id."));

        if (pagamento.getStatus().equals(Status.CONFIRMADO)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Pagamento já foi confirmado.");
        }

        pagamento.setStatus(Status.CONFIRMADO);
        repository.save(pagamento);
        pedidoClient.atualizaPagamento(pagamento.getPedidoId());



    }
}
