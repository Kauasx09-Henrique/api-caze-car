# 🚗 Loja de Carros — API

API REST para gestão de uma loja de carros: catálogo de veículos, anúncios de
vendedores, favoritos, propostas e chat entre clientes e vendedores.

Projeto **backend** desenvolvido em Java com Spring Boot, seguindo arquitetura em
camadas e boas práticas de mercado.

## 🛠️ Stack

| Camada          | Tecnologia                              |
|-----------------|------------------------------------------|
| Linguagem       | Java 21                                  |
| Framework       | Spring Boot 3.3                          |
| Persistência    | Spring Data JPA (Hibernate)              |
| Banco de dados  | PostgreSQL 16                            |
| Migrações       | Flyway                                   |
| Segurança       | Spring Security + JWT                    |
| Documentação    | springdoc-openapi (Swagger UI)           |
| Build           | Maven                                    |

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas:

```
controller  ->  service  ->  repository  ->  banco de dados
     |             |
    dto        regras de negócio
```

- **controller** — expõe os endpoints REST e trata requisições/respostas.
- **dto** — objetos de transporte de dados (entrada e saída da API).
- **service** — regras de negócio da aplicação.
- **repository** — acesso a dados via Spring Data JPA.
- **entity** — mapeamento objeto-relacional das tabelas.

## 🗄️ Modelo de dados

O schema é criado e versionado pelo Flyway (`src/main/resources/db/migration`),
com base na modelagem em 3ª Forma Normal:

- **Catálogo:** `marca`, `modelo`, `combustivel`, `cambio`, `carroceria`, `cor`
- **Veículos:** `veiculo`, `veiculo_foto`
- **Usuários:** `usuario` (ADMIN, VENDEDOR, CLIENTE)
- **Interações:** `favorito`, `proposta`, `conversa`, `mensagem`,
  `notificacao`, `historico_veiculo`

## ▶️ Como executar

### Pré-requisitos
- Java 21
- Docker (para o PostgreSQL) ou uma instância PostgreSQL local

### 1. Suba o banco de dados
```bash
docker compose up -d
```

### 2. Execute a aplicação
```bash
./mvnw spring-boot:run
# ou execute a classe LojaCarrosApiApplication pela IDE
```

A API sobe em `http://localhost:8080` e a documentação Swagger fica em
`http://localhost:8080/swagger-ui.html`.

## 🗺️ Roadmap

- [x] Estrutura inicial do projeto (Spring Boot + Maven)
- [x] Infraestrutura de banco (Docker Compose + Flyway)
- [ ] Entidades e repositórios
- [ ] DTOs e camada de serviço
- [ ] Endpoints REST (CRUD do catálogo e veículos)
- [ ] Autenticação e autorização (JWT)
- [ ] Favoritos, propostas e chat
- [ ] Notificações e histórico
- [ ] Testes automatizados
- [ ] Documentação OpenAPI completa
