# 📚 LibraHub

API REST para gerenciamento de uma biblioteca, desenvolvida com **Java e Spring Boot**.

O projeto tem como objetivo colocar em prática conceitos de desenvolvimento backend, arquitetura em camadas, persistência de dados e construção de APIs REST.

> 🚧 **Status:** Em desenvolvimento

---

## 🛠️ Tecnologias utilizadas

* **Java 25**
* **Spring Boot 4.1.1**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **Lombok**

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

**Controller**
Responsável por receber as requisições HTTP e disponibilizar os endpoints da API.

**Service**
Responsável pelas regras de negócio e pelo fluxo das operações.

**Repository**
Responsável pelo acesso e persistência dos dados utilizando Spring Data JPA.

**Entity**
Representa as entidades que serão persistidas no banco de dados.

---

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados.

Configuração utilizada durante o desenvolvimento:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/librahub
spring.datasource.username=postgres
spring.datasource.password=a
```

> ⚠️ Em um ambiente real, credenciais não devem ser armazenadas diretamente no arquivo de configuração versionado no Git. O ideal é utilizar variáveis de ambiente ou outro mecanismo seguro de configuração.

O Hibernate é responsável pela criação/atualização das tabelas através da configuração:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## 📖 Entidade Book

Atualmente, a aplicação possui a entidade `Book`, representando os livros cadastrados na biblioteca.

Cada livro possui:

| Campo    | Tipo   | Descrição              |
| -------- | ------ | ---------------------- |
| `id`     | Long   | Identificador do livro |
| `title`  | String | Título do livro        |
| `author` | String | Autor do livro         |
| `status` | Enum   | Status atual do livro  |

Os possíveis status são:

```text
AVAILABLE
BORROWED
LOST
DAMAGED
```

---

## 🌱 Dados iniciais

O projeto utiliza o arquivo `data.sql` para inserir dados iniciais no banco de dados durante a inicialização da aplicação.

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

## 🚀 Como executar o projeto

### 1. Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java 25
* Maven
* PostgreSQL

### 2. Criar o banco de dados

No PostgreSQL, crie o banco:

```sql
CREATE DATABASE librahub;
```

### 3. Configurar o acesso ao banco

Edite o arquivo:

```text
src/main/resources/application.properties
```

e configure as credenciais do PostgreSQL.

### 4. Executar a aplicação

Com o projeto aberto, execute a classe:

```text
SmrApplication
```

A aplicação será iniciada na porta:

```text
http://localhost:8080
```

---

## 🔌 Endpoints

Os endpoints estão sendo implementados gradualmente conforme o desenvolvimento do projeto.

### Livros

| Método   | Endpoint      | Descrição              |
| -------- | ------------- | ---------------------- |
| `GET`    | `/books`      | Lista os livros        |
| `GET`    | `/books/{id}` | Busca um livro por ID  |
| `POST`   | `/books`      | Cadastra um novo livro |
| `PUT`    | `/books/{id}` | Atualiza um livro      |
| `DELETE` | `/books/{id}` | Remove um livro        |

> 🚧 Alguns endpoints ainda estão em desenvolvimento.

---

## 🧪 Exemplo de resposta

Ao consultar os livros cadastrados:

```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "status": "AVAILABLE"
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "status": "AVAILABLE"
  }
]
```

---

## 🎯 Objetivos do projeto

O LibraHub está sendo desenvolvido principalmente para praticar:

* Desenvolvimento de APIs REST com Spring Boot
* Programação orientada a objetos com Java
* Arquitetura em camadas
* Spring Data JPA
* Hibernate
* Integração com PostgreSQL
* Persistência de dados
* Regras de negócio
* Tratamento de exceções
* Validação de dados
* Boas práticas de desenvolvimento backend

---

## 🔮 Próximos passos

O projeto continuará sendo evoluído com novas funcionalidades, incluindo:

* [ ] CRUD completo de livros
* [ ] Validação de dados
* [ ] Tratamento global de exceções
* [ ] Regras para empréstimo e devolução
* [ ] Gerenciamento de usuários
* [ ] Relacionamento entre usuários e livros
* [ ] DTOs
* [ ] Testes automatizados
* [ ] Documentação da API
* [ ] Melhorias na segurança da aplicação

---

## 👨‍💻 Sobre o projeto

O **LibraHub** é um projeto de estudo e portfólio desenvolvido para aprofundar conhecimentos em **Java, Spring Boot e desenvolvimento backend**.

A ideia é evoluir a aplicação gradualmente, aplicando os conceitos conforme eles são estudados e transformando o projeto em uma API cada vez mais completa.

**Em desenvolvimento ☕💻**
