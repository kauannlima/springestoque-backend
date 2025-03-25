# Controle de Estoque com Spring Boot e React

## Objetivo do Projeto

Este projeto tem como objetivo desenvolver um sistema de **Controle de Estoque** utilizando **Spring Boot** para o back-end e **React** para o front-end. Ele faz parte dos meus estudos em **Spring Boot** e **React**.

## Funcionalidades Principais

- **Cadastro de Produtos**: Permite o cadastro de novos produtos no sistema com informações como nome, categoria, descrição, fornecedor e quantidade inicial.
- **Movimentação de Estoque**: Registra as movimentações de entrada e saída de produtos, ajustando automaticamente a quantidade em estoque.
- **Gerenciamento de Setores**: Definição de setores para a movimentação de produtos, caso necessário (não é obrigatório para entradas).

## Estrutura de Diretórios

- `src/main/resources/db/`: Contém arquivos SQL para teste e para adicionar dados iniciais às tabelas.

## Tecnologias Utilizadas

- **Spring Boot** para o back-end
- **React** para o front-end
- **MySQL** como banco de dados
- **Swagger** para documentação da API

## Como Rodar o Projeto

1. Clone o repositório.
2. Configure o banco de dados MySQL e adicione as credenciais no arquivo `application.properties`.
3. Execute o back-end com o Spring Boot.
4. Inicie o front-end utilizando `npm start` ou `yarn start`.

## Contribuições

Este projeto está em andamento. Contribuições são bem-vindas, e a documentação detalhada sobre as regras de negócios e arquitetura pode ser encontrada na [Wiki](link_da_wiki).
