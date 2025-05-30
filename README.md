# API Bancária Simples 🏦

Olá! Este é o projeto **API Bancária Simples**, uma API REST desenvolvida com Spring Boot para simular operações bancárias básicas. A ideia é fornecer um backend robusto e claro para gerenciamento de contas e transações financeiras. Este README foi inspirado no formato do "Projeto Alura - Simulação de Plataforma Educacional".

O projeto visa demonstrar a construção de uma API funcional, com foco na lógica de negócios e boas práticas de desenvolvimento. Toda a interação com o sistema acontece via API REST.

## O que o projeto faz? 💻

O sistema conta com as seguintes funcionalidades principais:

### Gerenciamento de Contas:
* Permite criar novas contas bancárias, informando titular, número da conta e um saldo inicial opcional (caso não informado, o saldo inicial é zero).
* Possibilita listar todas as contas cadastradas no sistema.

### Operações em Contas:
* **Depósito**: Permite depositar um valor em uma conta específica.
* **Saque**: Permite sacar um valor de uma conta específica. O sistema verifica se há saldo suficiente antes de permitir o saque.

### Transações Financeiras:
* **Transferência**: Realiza transferências de valores entre duas contas distintas. O sistema verifica se a conta de origem possui saldo suficiente e se o valor da transferência é positivo.
* **Extrato**: Permite consultar o extrato de transações de uma conta específica dentro de um período determinado (data e hora de início e fim).

## Tecnologias Utilizadas 🛠️

Para construir este projeto, foram utilizadas as seguintes tecnologias e ferramentas:

* **Linguagem**: Java 17
* **Framework Principal**: Spring Boot 3.2.3
    * **Spring Web**: Para o desenvolvimento das APIs REST.
    * **Spring Data JPA**: Para facilitar a interação com o banco de dados e o mapeamento objeto-relacional (ORM).
* **Banco de Dados**: PostgreSQL
* **Build e Gerenciamento de Dependências**: Apache Maven (o wrapper está configurado para Maven 3.9.9)
* **Mapeamento de DTOs**: MapStruct 1.5.5.Final (para converter entre entidades e DTOs)
* **Redução de Código Boilerplate**: Lombok 1.18.30
* **Documentação da API**: SpringDoc OpenAPI (para geração automática da documentação Swagger UI)
* **Licença**: MIT

## O que você precisa para rodar o projeto? 🚀

* Java JDK 17 ou uma versão mais recente.
* Um servidor PostgreSQL instalado e rodando.
* Apache Maven.
* Sua IDE preferida (IntelliJ IDEA, Eclipse, VS Code, etc.).

## Colocando para funcionar ⚙️

Siga estes passos para executar o projeto:

1.  **Clone o Repositório**:
    ```bash
    git clone <url-do-repositorio-do-projeto>
    cd <diretorio-do-projeto>
    ```

2.  **Configure o Banco de Dados**:
    * Verifique se o seu servidor PostgreSQL está ativo.
    * Crie um banco de dados. O nome padrão configurado é `bancodb`.
    * Ajuste as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` no arquivo `src/main/resources/application.properties` conforme a configuração do seu ambiente PostgreSQL.
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/bancodb
        spring.datasource.username=seu_usuario_postgresql
        spring.datasource.password=sua_senha_postgresql
        ```
    * A estrutura do banco (tabelas, colunas, etc.) será gerenciada pelo Hibernate através da propriedade `spring.jpa.hibernate.ddl-auto=update`.
    * Dados iniciais serão carregados a partir do arquivo `src/main/resources/data.sql`, pois a propriedade `spring.sql.init.mode` está configurada como `always`.

3.  **Compile o Projeto**: Use o Maven para compilar o código e baixar as dependências:
    ```bash
    mvn clean install
    ```

4.  **Execute a Aplicação**: Com o projeto compilado, você pode iniciá-lo com o comando:
    ```bash
    mvn spring-boot:run
    ```
    Outra opção é executar a classe `BancoApiApplication` diretamente pela sua IDE.

Após esses passos, a aplicação estará rodando, geralmente no endereço `http://localhost:8080`.

## Endpoints da API: Onde tudo acontece 🌐

Aqui estão os principais "caminhos" (endpoints) que a API oferece. Você pode interagir com eles usando ferramentas como Postman, Insomnia ou diretamente pelo Swagger UI.

* **Documentação Interativa (Swagger UI)**:
    * `GET /swagger-ui.html`: Acesse a documentação completa e interativa da API.

* **Para Contas (`/api/contas`)**:
    * `POST /api/contas`: Cria uma nova conta bancária.
        * *O que enviar (corpo da requisição)*: Dados no formato `ContaRequestDTO` (JSON). Exemplo:
            ```json
            {
              "titular": "Nome do Titular",
              "numero": "12345-6",
              "saldoInicial": 100.50
            }
            ```
    * `GET /api/contas`: Lista todas as contas cadastradas.
    * `POST /api/contas/{id}/deposito`: Deposita um valor em uma conta específica.
        * *Path Variable*: `id` (o ID da conta).
        * *Request Parameter*: `valor` (o montante a ser depositado).
            * Exemplo: `POST /api/contas/1/deposito?valor=200`
    * `POST /api/contas/{id}/saque`: Saca um valor de uma conta específica.
        * *Path Variable*: `id` (o ID da conta).
        * *Request Parameter*: `valor` (o montante a ser sacado).
            * Exemplo: `POST /api/contas/1/saque?valor=50`

* **Para Transações (`/api/transacoes`)**:
    * `POST /api/transacoes/transferencia`: Realiza uma transferência entre contas.
        * *Request Parameters*: `origem` (ID da conta de origem), `destino` (ID da conta de destino), `valor` (montante a ser transferido).
            * Exemplo: `POST /api/transacoes/transferencia?origem=1&destino=2&valor=75`
    * `GET /api/transacoes/extrato`: Consulta o extrato de uma conta por período.
        * *Request Parameters*: `contaId` (ID da conta), `inicio` (data e hora de início do período, formato ISO: `YYYY-MM-DDTHH:mm:ss`), `fim` (data e hora de fim do período, formato ISO: `YYYY-MM-DDTHH:mm:ss`).
            * Exemplo: `GET /api/transacoes/extrato?contaId=1&inicio=2024-01-01T00:00:00&fim=2024-01-31T23:59:59`

## Inicialização do Banco com `data.sql` 💾

Para facilitar os testes e demonstração, o arquivo `src/main/resources/data.sql` contém comandos SQL para popular o banco de dados com dados iniciais (algumas contas e transações de depósito) assim que a aplicação é iniciada. Isso é possível graças à configuração `spring.sql.init.mode=always` no arquivo `application.properties`.

## Garantindo a Qualidade com Testes ✅

Para assegurar que as funcionalidades estão operando como esperado, o projeto conta com testes automatizados:

* **Testes Gerais da Aplicação** (`BancoApiApplicationTests.java`): Um teste básico que verifica se o contexto do Spring Boot (o "coração" da aplicação) está carregando sem problemas.

*Observação*: A cobertura de testes pode ser expandida para incluir testes unitários e de integração para os controllers, services e repositories, garantindo ainda mais a robustez da API.

---

Espero que esta documentação ajude a entender melhor o projeto! 😊
