package com.springestoque.springestoque_backend.config;

import com.springestoque.springestoque_backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErroResponse> handleNotFoundException(String mensagem, String detalhe, HttpStatus status) {
        ErroResponse erroResponse = new ErroResponse(
                status.value(),
                mensagem,
                detalhe
        );
        return new ResponseEntity<>(erroResponse, status);
    }

    @ExceptionHandler(CargoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleCargoNaoEncontradoException(CargoNaoEncontradoException ex) {
        return handleNotFoundException("Cargo não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return handleNotFoundException("Categoria não encontrada", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FornecedorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleFornecedorNaoEncontradoException(FornecedorNaoEncontradoException ex) {
        return handleNotFoundException("Fornecedor não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException ex) {
        return handleNotFoundException("Funcionario não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return handleNotFoundException("Produto não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SetorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleSetorNaoEncontradoException(SetorNaoEncontradoException ex) {
        return handleNotFoundException("Setor não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return handleNotFoundException("Usuario não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntidadeVinculadaException.class)
    public ResponseEntity<String> handleEntidadeVinculada(EntidadeVinculadaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        return handleNotFoundException("Erro inesperado", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
