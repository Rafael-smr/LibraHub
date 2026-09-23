# 📚 LibraHub

API REST para gerenciamento de uma biblioteca, desenvolvida com **Java e Spring Boot**.

O projeto tem como objetivo colocar em prática conceitos de desenvolvimento backend, arquitetura em camadas, persistência de dados, validação, tratamento de exceções e construção de APIs REST.

> 🚧 **Status:** Em desenvolvimento

---

## 🛠️ Tecnologias utilizadas

* Java 25
* Spring Boot 4.1.1
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Bean Validation
* Spring Boot DevTools

---

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura organizada em camadas, separando as responsabilidades da aplicação:

```text
src/
└── main/
    ├── java/
    │   └── com/libra/smr/
    │       ├── controller/
    │       ├── dto/
    │       ├── entity/
    │       ├── enums/
    │       ├── exception/
    │       ├── repository/
    │       ├── service/
    │       └── SmrApplication.java
    │
    └── resources/
        ├── application.properties
        └── data.sql
```

### Responsabilidades

* **Controller:** recebe as requisições HTTP e disponibiliza os endpoints da API.
* **Service:** concentra as regras de negócio e o fluxo das operações.
* **Repository:** responsável pelo acesso e persistência dos dados utilizando Spring Data JPA.
* **Entity:** representa as entidades persistidas no banco de dados.
* **DTO:** define os dados de entrada e saída da API, evitando a exposição direta das entidades.
* **Exception:** concentra as exceções e o tratamento de erros da aplicação.

---

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados.

Configuração utilizada durante o desenvolvimento:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/librahub
spring.datasource.username=postgres
```

> ⚠️ As credenciais do banco não devem ser armazenadas diretamente em arquivos versionados no Git. Em ambientes reais, recomenda-se utilizar variáveis de ambiente ou outro mecanismo seguro de configuração.

O Hibernate é responsável pela criação e atualização das tabelas através da configuração:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

# 📖 Entidades

## 📚 Book

A entidade `Book` representa os livros cadastrados na biblioteca.

Cada livro possui:

| Campo    | Tipo   | Descrição              |
| -------- | ------ | ---------------------- |
| `id`     | Long   | Identificador do livro |
| `title`  | String | Título do livro        |
| `author` | String | Autor do livro         |
| `status` | Enum   | Status atual do livro  |

### Status disponíveis

```text
AVAILABLE
BORROWED
LOST
DAMAGED
```

O status de um novo livro é definido automaticamente como:

```text
AVAILABLE
```

---

## 👤 Member

A entidade `Member` representa os usuários cadastrados na biblioteca.

Cada membro possui:

| Campo  | Tipo   | Descrição               |
| ------ | ------ | ----------------------- |
| `id`   | Long   | Identificador do membro |
| `name` | String | Nome do membro          |

O gerenciamento de empréstimos é separado da entidade `Member` através da entidade `Loan`.

---

# 📦 DTOs

O projeto utiliza **DTOs (Data Transfer Objects)** para separar os dados recebidos e enviados pela API das entidades persistidas no banco.

## BookRequestDto

Utilizado para receber os dados enviados pelo cliente ao criar ou atualizar um livro.

```text
title
author
```

Os campos possuem validações utilizando Bean Validation, como:

* `@NotBlank`
* `@Size`

O `id` não é enviado pelo cliente, pois é gerado automaticamente pelo banco.

O `status` também não é informado na criação, pois novos livros recebem automaticamente o status `AVAILABLE`.

## BookResponseDto

Utilizado para definir os dados retornados pela API:

```text
id
title
author
status
```

---

## MemberRequestDto

Utilizado para receber os dados enviados pelo cliente ao criar ou atualizar um membro.

```text
name
```

O campo possui validações utilizando Bean Validation.

## MemberResponseDto

Utilizado para definir os dados retornados pela API:

```text
id
name
```

---

# 🌱 Dados iniciais

O projeto utiliza o arquivo `data.sql` para inserir livros iniciais no banco de dados durante a inicialização da aplicação.

Exemplo:

```sql
INSERT INTO tb_book (title, author, status)
VALUES
    ('Clean Code', 'Robert C. Martin', 'AVAILABLE'),
    ('Effective Java', 'Joshua Bloch', 'AVAILABLE'),
    ('The Pragmatic Programmer', 'Andrew Hunt', 'BORROWED'),
    ('Java: Como Programar', 'Deitel', 'AVAILABLE'),
    ('Design Patterns', 'Erich Gamma', 'DAMAGED');
```

---

# 🚀 Como executar o projeto

## 1. Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java 25
* Maven
* PostgreSQL

## 2. Criar o banco de dados

No PostgreSQL, crie o banco:

```sql
CREATE DATABASE librahub;
```

## 3. Configurar o acesso ao banco

Edite o arquivo:

```text
src/main/resources/application.properties
```

e configure as credenciais do PostgreSQL de acordo com o seu ambiente.

## 4. Executar a aplicação

Com o projeto aberto, execute a classe:

```text
SmrApplication
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

---

# 🔌 Endpoints

## 📚 Books

O módulo de livros possui um CRUD completo.

| Método   | Endpoint      | Descrição              |
| -------- | ------------- | ---------------------- |
| `GET`    | `/books`      | Lista todos os livros  |
| `GET`    | `/books/{id}` | Busca um livro por ID  |
| `POST`   | `/books`      | Cadastra um novo livro |
| `PUT`    | `/books/{id}` | Atualiza um livro      |
| `DELETE` | `/books/{id}` | Remove um livro        |

### Exemplo — POST `/books`

```json
{
    "title": "Clean Architecture",
    "author": "Robert C. Martin"
}
```

O status será definido automaticamente como `AVAILABLE`.

### Exemplo de resposta

```json
{
    "id": 1,
    "title": "Clean Architecture",
    "author": "Robert C. Martin",
    "status": "AVAILABLE"
}
```

---

## 👤 Members

O módulo de membros possui um CRUD completo.

| Método   | Endpoint        | Descrição               |
| -------- | --------------- | ----------------------- |
| `GET`    | `/members`      | Lista todos os membros  |
| `GET`    | `/members/{id}` | Busca um membro por ID  |
| `POST`   | `/members`      | Cadastra um novo membro |
| `PUT`    | `/members/{id}` | Atualiza um membro      |
| `DELETE` | `/members/{id}` | Remove um membro        |

### Exemplo — POST `/members`

```json
{
    "name": "Gabriel"
}
```

### Exemplo de resposta

```json
{
    "id": 1,
    "name": "Gabriel"
}
```

---

# ⚠️ Validação e tratamento de exceções

A aplicação utiliza **Bean Validation** para validar os dados recebidos pela API.

Exemplos de validações utilizadas:

* `@NotBlank`
* `@Size`
* `@NotNull`

Também existe um tratamento global de exceções através de `@RestControllerAdvice`.

Entre as exceções tratadas está a tentativa de buscar um livro ou membro que não existe.

---

# 🎯 Objetivos do projeto

O LibraHub está sendo desenvolvido principalmente para praticar:

* Desenvolvimento de APIs REST com Spring Boot
* Programação orientada a objetos com Java
* Arquitetura em camadas
* Spring Data JPA
* Hibernate
* Integração com PostgreSQL
* Persistência de dados
* DTOs
* Bean Validation
* Tratamento de exceções
* Regras de negócio
* Relacionamentos entre entidades
* Boas práticas de desenvolvimento backend

---

# 🔮 Próximos passos

O projeto continuará sendo evoluído com novas funcionalidades, incluindo:

* [x] CRUD de livros
* [x] CRUD de membros
* [x] DTOs
* [x] Validação de dados
* [x] Tratamento de exceções
* [ ] Sistema de empréstimos
* [ ] Regras para empréstimo e devolução
* [ ] Relacionamento entre livros, membros e empréstimos
* [ ] Testes automatizados
* [ ] Documentação da API
* [ ] Melhorias na segurança da aplicação

---

# 👨‍💻 Sobre o projeto

O **LibraHub** é um projeto de estudo e portfólio desenvolvido para aprofundar conhecimentos em **Java, Spring Boot e desenvolvimento backend**.

A aplicação está sendo evoluída gradualmente, aplicando os conceitos conforme são estudados e transformando o projeto em uma API cada vez mais completa.

A documentação deste projeto foi desenvolvida com auxílio de **Inteligência Artificial** durante o processo de desenvolvimento e organização do projeto.

Em desenvolvimento ☕💻
