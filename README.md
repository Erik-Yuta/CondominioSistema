# 🏘️ Sistema de Condomínio


## 🎓 Trabalho Acadêmico
O projeto foi criado para demonstrar a aplicação de conceitos de **Programação Orientada a Objetos (POO)** e **Persistência de Dados** em uma aplicação Desktop completa.

---

## 📌 Funcionalidades

O sistema foi concebido para gerenciar informações de usuários (moradores ou administradores) em um condomínio. As principais funcionalidades implementadas são:

* **CRUD de Usuários:** Interface completa para **C**riar (Adicionar), **R**ealizar a Leitura (Consultar na Tabela), **U**pdate (Editar) e **D**elete (Excluir) de registros de usuários.
* **Interface Gráfica (GUI):** Utilização do **Java Swing** para criar telas interativas e amigáveis para o gerenciamento de dados.
* **Persistência de Dados:** Conexão com banco de dados **MySQL** para garantir que os dados sejam salvos e recuperados de forma persistente.

---

## 💻 Tecnologias Utilizadas

| Componente | Tecnologia | Detalhes |
| :--- | :--- | :--- |
| **Linguagem** | **Java** | Aplicação principal. |
| **Interface** | **Java Swing** | Criação das janelas (`CondominioFrame`, `UsuarioDialog`, `UsuarioEditar`). |
| **Banco de Dados** | **MySQL** | SGBD utilizado para persistência. |
| **Conexão DB** | **JDBC** | Driver Java para a comunicação com o MySQL. |


---

## 🛠️ Como Configurar e Executar

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente:

### 1. Requisitos

* **Java Development Kit (JDK)** (Recomendado: Versão 8 ou superior).
* **MySQL Server** (Instalado e em execução).
* **MySQL Workbench** ou ferramenta similar para executar o script SQL.
* Uma IDE Java (IntelliJ IDEA, Eclipse, etc.).

### 2. Configuração do Banco de Dados

1.  Abra seu cliente MySQL e crie um novo banco de dados.
2.  Execute o script **`condominio.sql`** (presente na raiz do projeto) para criar a estrutura da tabela necessária.
3.  **Ajuste a Conexão:** Edite o arquivo **`src/com/condominio/Conexao.java`** e atualize as seguintes variáveis com suas credenciais do MySQL:
    * `USUARIO` (Seu usuário do MySQL, ex: `root`)
    * `SENHA` (Sua senha do MySQL)
    * `URL` (Endereço da conexão, ex: `jdbc:mysql://localhost:3306/condominio`)

### 3. Execução da Aplicação

1.  Baixe e configure o **driver JDBC do MySQL** em seu projeto (adicione o arquivo `.jar` ao *Build Path* da sua IDE).
2.  Compile o projeto.
3.  Execute a classe principal **`src/com/condominio/SistemaCondominio.java`**.

---



---
