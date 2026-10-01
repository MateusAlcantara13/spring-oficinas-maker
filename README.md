# Oficinas do Laboratório Maker

Cadastro e ciclo de vida das oficinas de um laboratório maker: rascunho, publicação, inscrições, encerramento e cancelamento.

Projeto de estudo desenvolvido durante o curso de **Análise e Desenvolvimento de Sistemas** (SENAI-SP Vila Leopoldina), na disciplina de desenvolvimento web com Spring Boot.

## Stack

- Java 21
- Spring Boot (Spring Web + Thymeleaf)
- Bean Validation (Jakarta)
- Lombok
- Maven

Os dados ficam **em memória** (uma `List` dentro do repositório). Não há banco de dados — ao reiniciar a aplicação, os registros são perdidos.

## Como rodar

```bash
./mvnw spring-boot:run
```

Ou abra o projeto no IntelliJ e execute a classe `OficinaApplication`.

Depois acesse: **http://localhost:8080/oficinas**

## Rotas

| Método | Rota | O que faz |
|---|---|---|
| GET | `/oficinas` | Lista todas as oficinas |
| GET | `/oficinas/novo` | Exibe o formulário de cadastro |
| POST | `/oficinas` | Valida e salva a oficina |
| GET | `/oficinas/{id}` | Detalhes de uma oficina |
| GET | `/oficinas/publicadas` | Lista as oficinas publicadas e o total |
| POST | `/oficinas/{id}/publicar` | RASCUNHO → PUBLICADA |
| POST | `/oficinas/{id}/inscrever` | Registra uma inscrição |
| POST | `/oficinas/{id}/encerrar` | PUBLICADA → ENCERRADA |
| POST | `/oficinas/{id}/cancelar` | Cancela a oficina |

## Estrutura

```
src/main/java/org/example/oficina/
├── model/Oficina.java
├── model/CategoriaOficina.java   (enum)
├── model/SituacaoOficina.java    (enum)
├── repository/OficinaRepository.java
└── controller/OficinaController.java
src/main/resources/templates/
├── oficinas.html     (listagem)
├── form.html         (cadastro)
├── detalhes.html     (uma oficina)
└── publicadas.html   (listagem filtrada + contador)
```

## O que esse projeto exercita

- Máquina de estados completa com 4 situações
- Contador de inscritos com limite de vagas (`possuiVagas()` / `vagasRestantes()`)
- Separação entre **dado guardado** (`vagas`, `inscritos`) e **dado calculado** (`vagasRestantes()`)
- Validação com `@Email`, `@Min`, `@Max`
- Botões que aparecem apenas na situação correta, via `th:if`
- Redirecionamento de volta para a página de detalhes após a ação
