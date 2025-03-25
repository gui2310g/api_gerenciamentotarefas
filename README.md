
# Api Gerenciamento Tarefas

Este é uma api desenvolvido com Spring Boot e Docker do projeto full-stack de gerenciamento de tarefas, a api consiste em criação de tarefas com sistema de roles, com sistema de autenticação onde usuario cria suas tarefa com opções de editar, atualizar e excluir, e o admin com opção de visualizar e excluir usuarios


## Ferramentas Utilizadas

#### Spring Boot
#### Spring Security 
#### Spring Boot JPA
#### JWT (JSON Web Token)
#### Lombok 
#### MapStruct
#### PostgreSQL
#### Docker



## Requisitos

#### Java 21
#### PostgresSQL
#### Docker
## Configuração

Clona o projeto

```bash
https://github.com/gui2310g/api_gerenciamentotarefas.git
```

Realize esse comando com o docker baixado.

Clona o projeto

```bash
docker compose up --build
```

Para executar fora do docker, execute: 
```bash
mvn spring-boot:run
```

**Obs: certifique de configurar seu application.properties e application-docker.properties***

(Opcional) Caso deseja para testes rodar o front-end, execute o comando:
```bash
docker pull gui2310g/spa-gerenciamentotarefas:latest
```
## Endpoints

### Auth 
#### Retorna todos os itens

```http
  POST /auth
```

### Users 

#### Cria um usuario


```http
  POST /users
```
#### Retorna todos os usuarios (**Admin**)

```http
  GET /users
```

#### Retorna um usuario selecionado por id (**Admin**)
```http
  GET /users/{id}
```
| Parameter | Type      | Description                                          |
|:----------|:----------|:---------------------------------------------------  |
| `id`      | `integer` | **Obrigatorio**. a id do usuario que deseja retornar |

#### Retorna um usuario atualizado (**User**)

```http
  PUT /users
```

***Obs: É necessario estar autenticado***

#### Deleta um usuario (**Admin**)

```http
  DELETE /users
```
| Parameter | Type      | Description                                          |
|:----------|:----------|:---------------------------------------------------  |
| `id`      | `integer` | **Obrigatorio**. a id do usuario que deseja deletar |

### Tasks (**User**)
***Obs: Todos os endpoint abaixo é necessario estar autenticado***

#### Cria uma task 
```http
  POST /tasks
```
#### Retorna todas tasks

```http
  GET /tasks
```

#### Retorna todas tasks

```http
  GET /tasks
```

#### Retorna todas tasks de um usuario logado

```http
  GET /tasks/findByAuth
```

#### Retorna uma tasks selecionado por id
```http
  GET /tasks/{id}
```
| Parameter | Type      | Description                                          |
|:----------|:----------|:---------------------------------------------------  |
| `id`      | `integer` | **Obrigatorio**. a id da task que deseja retornar |

#### Retorna uma task atualizado 

```http
  PUT /tasks
```

#### Deleta uma task

```http
  DELETE /tasks
```

| Parameter | Type      | Description                                          |
|:----------|:----------|:---------------------------------------------------  |
| `id`      | `integer` | **Obrigatorio**. a id da task que deseja deletar     |


Coloque qualquer informação adicional aqui

## Contribuir

Caso queria contribuir para o projeto, abre uma pull request apontando erros ou features.
