# Desafio: Gateway | Brasil API

## Descrição

Gateway para consulta de CEPs utilizando a API do Brasil API. Após a consulta, os dados são armazenados em um banco de dados MySQL.

## Tecnologias

- Java 11
  - Maven para gerenciamento de dependências
  - HttpClient nativo para requisições HTTP
  - Hibernate para conexão e persistência de dados no banco
- My SQL 8

## Execução

### .env

É necessário criar um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
DATABASE_URL=jdbc:mysql://<database-address>/<database-name>
DATABASE_NAME=<database-name>
DATABASE_USER=root
DATABASE_PASSWORD=<database-password>
```

### Banco de Dados

O docker pode ser utilizado para subir o banco de dados com mais facilidade. O `docker-compose.yml` na raiz do projeto lê o `.env` e sobe o banco de dados com as configurações fornecidas.

```bash
docker compose up -d
```

O hibernate cria a tabela `cep` automaticamente ao executar o projeto.

### Executando

Utilize sua IDE de preferência para executar o projeto. O arquivo `Main.java` contém o método `main` que roda a aplicação.

## Como usar?

Para consultar e armazenar um CEP, basta passar o CEP como argumento ao executar o projeto. 
 
No IntelliJ IDEA, por exemplo, é possível passar argumentos do programa na aba de configurações da execução.

É possível passar mais de um CEP como argumento separando por espaços. As consultaas são feitas uma a uma.

Caso nenhum argumento seja passado, o programa lista todos os CEPs armazenados no banco.

## Como gerar um executável?

```bash
mvn clean package
```

O JAR é gerado na pasta `target/` com o nome de `cep-desafio-brasilapi-1.0-SNAPSHOT-jar-with-dependencies.jar`. 

Para executar o JAR, é necessário ter um .env na mesma pasta que o JAR, de onde as configurações de acesso ao banco de dados serão lidas.

```bash
java -jar target/cep-desafio-brasilapi-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Lembrando que o cep é opcional. Caso não seja passado, o programa lista todos os CEPs armazenados no banco. Também podem ser passados mais de um CEP separando por espaços.

```bash
java -jar target/cep-desafio-brasilapi-1.0-SNAPSHOT-jar-with-dependencies.jar 01001000 01001001 01001002
```