package com.springestoque.springestoque_backend.config;

import com.springestoque.springestoque_backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErroResponse> buildErrorResponse(String mensagem, String detalhe, HttpStatus status) {
        ErroResponse erroResponse = new ErroResponse(
                status.value(),
                mensagem,
                detalhe
        );
        return new ResponseEntity<>(erroResponse, status);
    }

    @ExceptionHandler(CargoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleCargoNaoEncontradoException(CargoNaoEncontradoException ex) {
        return buildErrorResponse("Cargo não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return buildErrorResponse("Categoria não encontrada", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FornecedorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleFornecedorNaoEncontradoException(FornecedorNaoEncontradoException ex) {
        return buildErrorResponse("Fornecedor não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException ex) {
        return buildErrorResponse("Funcionário não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return buildErrorResponse("Produto não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SetorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleSetorNaoEncontradoException(SetorNaoEncontradoException ex) {
        return buildErrorResponse("Setor não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntidadeVinculadaException.class)
    public ResponseEntity<ErroResponse> handleEntidadeVinculadaException(EntidadeVinculadaException ex) {
        return buildErrorResponse("Entidade Vinculada", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<String> handleEstoqueInsuficiente(EstoqueInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TipoMovimentacaoNotFoundException.class)
    public ResponseEntity<String> handleTipoMovimentacaoNotFound(TipoMovimentacaoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovimentacaoEstoqueNaoEncontradaException.class)
    public ResponseEntity<String> handleMovimentacaonNotFound(MovimentacaoEstoqueNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ManutencaoNaoEncontradaException.class)
    public ResponseEntity<String> handleManutencaoNotFound(ManutencaoNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StatusManutencaoJaAlteradoException.class)
    public ResponseEntity<String> handleStatusManutencaoJaAlterado(StatusManutencaoJaAlteradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<String> handleSenhaIncorreta(SenhaIncorretaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        return buildErrorResponse("Erro inesperado", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}