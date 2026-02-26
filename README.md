#  Controle de Estoque

Sistema de Controle de Estoque desenvolvido com Java + Spring Boot + SQL Server + Docker.

Projeto criado com foco em aprendizado backend e portfólio, aplicando boas práticas de arquitetura em camadas, persistência com JPA e isolamento de banco de dados com Docker.



##  Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Data JPA
- Hibernate
- SQL Server
- Docker
- Docker Compose
- Maven
- Jakarta Validation
- JUnit 5
- Mockito



##  Objetivo do Projeto

Simular um sistema de controle de estoque com:

- Cadastro de categorias
- Cadastro de produtos
- Atualização de produtos
- Venda de produtos (baixa automática de estoque)
- Remoção de categorias
- Remoção de produtos
- Persistência em banco relacional
- Ambiente isolado via container Docker
- Testes unitários na camada de serviço



##  Arquitetura

O projeto segue arquitetura em camadas:

controller → service → repository → database

###  Estrutura
src/main/java/com/guilherme/controle_estoque

├── controller

├── service

├── repository

├── model

├── dto

└── resources

### Responsabilidades

- Controller → Recebe requisições HTTP
- Service → Regras de negócio e validações
- Repository → Comunicação com o banco via JPA
- Model → Entidades mapeadas
- DTO → Objetos de entrada da API



##  Banco de Dados com Docker

O projeto utiliza SQL Server rodando em container.


##  Subir o banco

Com Docker Desktop em execução, rode na raiz do projeto:

docker compose up -d

O SQL Server ficará disponível em: localhost,1434

Crie o banco(apenas na primeira execução):

CREATE DATABASE controle_estoque;

##  Configuração da Aplicação

1. Copie application-example.properties
 
2. Renomeie para application.properties
 
2. Configure usuário e senha

O arquivo application.properties está no .gitignore.



##  Execução

1. Subir o banco (Docker)
 
2. Executar a aplicação Spring Boot
  
3. Testar endpoints via Postman


## Endpoints da API

### Categorias

#### - Listar categorias

GET /categorias

#### - Criar categoria

POST /categorias

Body:

{

  "nome": "Eletrônicos"
  
}

#### - Deletar categoria

DELETE /categorias/{id}

### Produtos

#### - Criar produto

POST /produtos

Body:

{

"nome": "Notebook",

"categoriaId": 1,

"preco": 2500,

"quantidadeEmEstoque": 10

}

#### - Listar produtos

GET /produtos

#### - Buscar produto por ID

GET /produtos/{id}

#### - Atualizar produto

PUT /produtos/{id}

#### - Deletar produto

DELETE /produtos/{id}

#### - Realizar venda (baixa no estoque)

POST /produtos/{id}/vender

Body:

{

  "quantidade": 2
  
}

A venda reduz automaticamente a quantidade disponível no estoque.

## Retorno de Status HTTP

A API segue boas práticas REST utilizando códigos de status apropriados:

 - 200 OK → Operação realizada com sucesso
 - 201 Created → Recurso criado com sucesso
 - 204 No Content → Recurso removido com sucesso
 - 400 Bad Request → Dados inválidos
 - 404 Not Found → Produto ou categoria não encontrados
 - 409 Conflict → Estoque insuficiente
 - 500 Internal Server Error → Erro inesperado


## Persistência

 • O volume estoque_data garante que os dados não sejam apagados ao reiniciar o container.
 
 • Evitar utilizar docker compose down -v, pois isso remove o volume.



## Conceitos Aplicados

 • REST API

 • Arquitetura em camadas
 
 • DTO Pattern (utilizando record)
 
 • Injeção de dependência
 
 • JPA / Hibernate
 
 • Relacionamento entre entidades
 
 • Validação com Jakarta Validation
 
 • Tratamento de exceções
 
 • Isolamento de banco com Docker
 
 • Volume para persistência de dados

 • Retorno adequado de status HTTP

 • Testes unitários na camada de serviço (JUnit + Mockito)

## Autor

Guilherme Machado

Estudante de Ciência da Computação – UERJ

Foco em Backend Java
