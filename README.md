# 🔐 Validador de Senhas — API REST

API REST desenvolvida em **Java 15 + Spring Boot 2.7.18** para validação de senhas segundo critérios de segurança definidos.

---

## 📋 Regras de Validação

Uma senha é considerada **válida** quando atende a **todos** os critérios abaixo:

| Critério | Detalhe |
|---|---|
| Comprimento mínimo | Ao menos **9 caracteres** |
| Dígito numérico | Ao menos **1 dígito** (0–9) |
| Letra minúscula | Ao menos **1 letra minúscula** |
| Letra maiúscula | Ao menos **1 letra maiúscula** |
| Caractere especial | Ao menos **1** dos seguintes: `!@#$%^&*()-+` |
| Sem repetição | **Nenhum caractere repetido** |
| Sem espaços | Espaços em branco **invalidam** a senha |

### Exemplos

```
IsValid("")          → false  (vazia)
IsValid("aa")        → false  (muito curta, com repetição)
IsValid("ab")        → false  (muito curta)
IsValid("AAAbbbCc")  → false  (sem dígito)
IsValid("AbTp9!foo") → false  ('o' repetido)
IsValid("AbTp9!foA") → false  ('A' repetido)
IsValid("AbTp9 fok") → false  (espaço em branco)
IsValid("AbTp9!fok") → true   ✅
```

---

## 🚀 Como Executar

### Pré-requisitos

- Java 11 ou superior (recomendado Java 15+)
- Maven 3.8+

> ⚠️ **Importante:** este projeto usa **Spring Boot 2.7.x**, que é compatível com Java 11 até Java 19.
> O Spring Boot 3.x **não é compatível** com este projeto sem migrações adicionais — veja a seção [Compatibilidade de Versões](#-compatibilidade-de-versões).

### Rodando a aplicação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/validador-senha.git
cd validador-senha

# Execute com Maven Wrapper
./mvnw spring-boot:run

# Ou com Maven instalado
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### Rodando os testes

```bash
./mvnw test
```

---

## 🌐 Uso da API

### Endpoint

```
POST /api/v1/senhas/validar
Content-Type: application/json
```

### Requisição

```json
{
  "senha": "AbTp9!fok"
}
```

### Resposta — Senha válida (`200 OK`)

```json
{
  "valida": true
}
```

### Resposta — Senha inválida (`200 OK`)

```json
{
  "valida": false
}
```

### Resposta — Requisição malformada (`400 Bad Request`)

```json
{
  "erro": "O campo 'senha' é obrigatório."
}
```

### Documentação Interativa (Swagger UI)

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

---

## 🏗️ Arquitetura e Decisões Técnicas

### Padrão Strategy

Cada regra de validação foi encapsulada em uma classe independente que implementa a interface `ValidadorSenha`:

```
validadorsenha/
├── validador/
│   ├── ValidadorSenha.java                    ← Interface (Strategy)
│   └── impl/
│       ├── ValidadorComprimentoMinimo.java
│       ├── ValidadorDigito.java
│       ├── ValidadorLetraMinuscula.java
│       ├── ValidadorLetraMaiuscula.java
│       ├── ValidadorCaractereEspecial.java
│       └── ValidadorSemCaracteresRepetidos.java
├── servico/
│   └── ServicoValidacaoSenha.java             ← Orquestra as regras
├── controller/
│   └── SenhaController.java                   ← Endpoint REST
├── dto/
│   ├── RequisicaoSenha.java
│   └── RespostaValidacao.java
└── excecao/
    └── TratadorGlobalDeExcecoes.java
```

**Por que Strategy?**
- Cada classe tem **uma única responsabilidade** (Princípio SRP do SOLID)
- Adicionar ou remover regras não requer modificar o serviço (Princípio OCP)
- O Spring injeta automaticamente todos os beans `ValidadorSenha` na lista do serviço

### Por que `POST` e não `GET`?

Senhas não devem ser enviadas como parâmetros de URL. Parâmetros de query ficam expostos em:
- Logs de servidores e proxies
- Histórico de navegadores
- Headers de `Referer`

O método `POST` com corpo JSON evita essas exposições.

### Por que `200 OK` mesmo para senha inválida?

A requisição em si foi processada com sucesso. O resultado da validação (`true`/`false`) é a resposta correta para a pergunta feita, não um erro da API. Retornar `422 Unprocessable Entity` seria semanticamente incorreto aqui, pois a senha inválida não é um erro de entrada — é o resultado esperado do processamento.

---

## ⚙️ Compatibilidade de Versões

### Por que Spring Boot 2.7.x e não 3.x?

O **Spring Boot 3.x** introduziu uma mudança de namespace importante:

| Versão | Namespace de validação / servlet |
|---|---|
| Spring Boot 2.x | `javax.validation.*`, `javax.servlet.*` |
| Spring Boot 3.x | `jakarta.validation.*`, `jakarta.servlet.*` |

Além disso, o **Spring Boot 3.x exige Java 17 como versão mínima**, pois seus JARs são compilados com bytecode Java 17 (class file version 61).

Se você tentar usar Spring Boot 3.x com Java 15 ou inferior, encontrará os seguintes erros:

```
[ERROR] release version 17 not supported
[ERROR] cannot find symbol — SpringApplication, ResponseEntity, etc.
```

Para migrar este projeto para Spring Boot 3.x no futuro, seria necessário:
1. Atualizar para **Java 17+**
2. Substituir todos os imports `javax.*` por `jakarta.*`
3. Trocar `springdoc-openapi-ui:1.x` por `springdoc-openapi-starter-webmvc-ui:2.x`

---

## 🧪 Testes

A suíte de testes cobre três camadas:

| Camada | Tipo | Ferramenta |
|---|---|---|
| Validadores individuais | Testes unitários | JUnit 5 |
| Serviço de validação | Testes de integração da camada | JUnit 5 + Parameterized |
| Controller REST | Testes de integração HTTP | Spring MockMvc |

Os testes de serviço utilizam `@ParameterizedTest` com todos os exemplos do enunciado do desafio, garantindo que o comportamento esperado está coberto.

---

## 🧩 Premissas Assumidas

| Premissa | Motivação |
|---|---|
| Espaço em branco invalida a senha | O enunciado cita explicitamente que espaços não são caracteres válidos |
| A comparação de repetição é **case-sensitive** | `A` e `a` são caracteres diferentes em Unicode; não há indicação contrária no enunciado |
| `null` retorna `false` (sem lançar exceção) | Evita que a API quebre; o campo `senha` é validado como `@NotNull` antes de chegar ao serviço |
| Caracteres especiais são exatamente `!@#$%^&*()-+` | Enumeração explícita no enunciado; não foram adicionados outros |

---

## 🛠️ Tecnologias Utilizadas

- **Java 15** (compatível com Java 11+)
- **Spring Boot 2.7.18** — última versão estável da linha 2.x
- **Spring Web** — API REST
- **Spring Validation** — validação de entrada via `javax.validation` (`@NotNull`, `@Valid`)
- **Springdoc OpenAPI 1.7.0** — documentação Swagger (compatível com Spring Boot 2.x)
- **JUnit 5** — testes unitários e de integração
- **Maven** — gerenciamento de dependências e build
