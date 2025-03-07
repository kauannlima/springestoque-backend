package com.springestoque.springestoque_backend.config;

import com.springestoque.springestoque_backend.exception.CategoriaNaoEncontradaException;
import com.springestoque.springestoque_backend.exception.FornecedorNaoEncontradoException;

import com.springestoque.springestoque_backend.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

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

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return handleNotFoundException("Categoria não encontrada", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FornecedorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleFornecedorNaoEncontradoException(FornecedorNaoEncontradoException ex) {
        return handleNotFoundException("Fornecedor não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
        return handleNotFoundException("Produto não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        return handleNotFoundException("Erro inesperado", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
