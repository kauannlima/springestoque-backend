# Spring Estoque Backend

Este é o repositório do backend de um sistema de controle de estoque desenvolvido utilizando **Spring Boot** e **JPA**. O sistema permite o cadastro de produtos, movimentações de entrada e saída de estoque, e manutenção dos itens.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **JPA (Hibernate)**: Para interação com o banco de dados.
- **PostgreSQL**: Banco de dados utilizado para persistência de dados.
- **Maven**: Gerenciador de dependências.
- **JUnit 5**: Framework de testes unitários.

## Funcionalidades

- **Cadastro de Produtos**: Permite o cadastro de produtos no sistema, com informações como nome, categoria, fornecedor, descrição e quantidade em estoque (inicialmente definida como 0).
  
- **Movimentação de Estoque**:
  - **Entrada**: A quantidade informada é somada ao estoque do produto.
  - **Saída**: A quantidade informada é subtraída do estoque do produto, com a restrição de que não é possível registrar uma saída se a quantidade em estoque for inferior à quantidade a ser retirada.
  - **Manutenção**: A quantidade retirada do estoque para manutenção, com reabastecimento após a conclusão da manutenção.

## Instruções de Execução

1. Clone o repositório:
    ```bash
    git clone https://github.com/kauannlima/springestoque-backend.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd springestoque-backend
    ```

3. Execute o projeto usando Maven:
    ```bash
    mvn spring-boot:run
    ```

4. Acesse a API na URL:
    ```
    http://localhost:8080
    ```

## Banco de Dados

O projeto utiliza o PostgreSQL como banco de dados. Você pode criar o banco de dados e importar os arquivos SQL que estão localizados no diretório `src/main/resources/db` para realizar testes e popular as tabelas.

Os arquivos SQL são:

- **populate_db.sql**: Script para popular as tabelas com dados de teste.
- **create_tables.sql**: Script para criação das tabelas do banco de dados.

## Estrutura de Diretórios

- **src/main/java**: Contém a lógica de negócios da aplicação.
- **src/main/resources**:
  - **application.properties**: Configurações do Spring Boot.
  - **db**: Contém os arquivos SQL para criação e inserção de dados no banco de dados.

## Regras de Negócios - Cadastro e Movimentação de Produtos

### Cadastro de Produtos

1. **Cadastro de Produto**: Ao cadastrar um produto, as seguintes informações são registradas:
    - **Nome do produto**
    - **Categoria**
    - **Descrição**
    - **Quantidade em estoque** (no cadastro foi alterado para sempre ser cadastrado com 0 quantidades)
    - **Fornecedor**

2. **Valor Atual do Estoque**: O valor de **quantidade em estoque** é atualizado conforme as movimentações realizadas, como **entradas** e **saídas**.

---

### Movimentação de Estoque

O sistema permite registrar três tipos principais de movimentação que afetam a quantidade em estoque de um produto:

1. **ENTRADA** ("Compra - Entrada"):
    - **Descrição**: Quando uma movimentação do tipo **ENTRADA** é realizada, ela **adiciona** ao estoque do produto.
    - **Processo**: A quantidade informada na movimentação é **somada** à quantidade atual de estoque do produto.
    - **Exemplo**: Se o estoque do produto "Produto A" estava com 50 unidades e uma movimentação de **ENTRADA** de 30 unidades é registrada, o estoque do "Produto A" será atualizado para 80 unidades.

2. **SAÍDA** ("Uso Interno - Saída"):
    - **Descrição**: Quando uma movimentação do tipo **SAÍDA** ocorre, a quantidade informada é **subtraída** do estoque do produto.
    - **Processo**: A quantidade informada na movimentação será **subtraída** da quantidade atual de estoque.
    - **Exemplo**: Se o estoque do produto "Produto A" estava com 80 unidades e uma movimentação de **SAÍDA** de 10 unidades é registrada, o estoque do "Produto A" será atualizado para 70 unidades.
    - **Restrição**: Não será possível registrar uma **SAÍDA** se a quantidade em estoque do produto for inferior à quantidade que se deseja retirar.

3. **MANUTENÇÃO** ("Manutenção"):
    - **Descrição**: Quando uma movimentação do tipo **MANUTENÇÃO** ocorre, a quantidade informada é **subtraída** do estoque do produto durante o processo de manutenção.
    - **Processo**: A quantidade de unidades é **subtraída** do estoque enquanto o produto está em manutenção.
    - **Atualização após Conclusão**: Quando o status da manutenção é alterado para **"Concluído"**, a quantidade do produto que foi retirada durante a manutenção é **adicionada** de volta ao estoque.
    - **Exemplo**:
        - Inicialmente, o produto "Produto A" tem 100 unidades em estoque.
        - Durante a manutenção, 20 unidades são retiradas do estoque. O estoque do "Produto A" agora é 80 unidades.
        - Após a conclusão da manutenção, as 20 unidades retiradas são adicionadas de volta ao estoque, atualizando o estoque para 100 unidades novamente.

---

## Contribuições

Para contribuir com o projeto, siga os passos abaixo:

1. Faça um fork do repositório.
2. Crie uma branch para a sua feature (`git checkout -b feature/MinhaFeature`).
3. Faça as modificações e commit (`git commit -am 'Adiciona nova feature'`).
4. Envie para o repositório remoto (`git push origin feature/MinhaFeature`).
5. Abra um Pull Request.

---

## Licença

Este projeto é licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
