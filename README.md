# 🌤️ Clima Bagual

Aplicativo desktop completo para consulta e histórico do clima em cidades brasileiras, desenvolvido em Java com interface Swing e acesso a dados da API [Open-Meteo](https://open-meteo.com/).

## 📌 Funcionalidades

- Pesquisa por cidade e estado
- Visualização do clima atual, diário e por hora
- Histórico das consultas
- Interface amigável com ícones e design responsivo
- Banco de dados com todos os estados e cidades brasileiras
- Uso de API gratuita com dados em tempo real

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Swing (GUI)
- JDBC + MySQL
- API REST (HTTP Client)
- Gson (JSON)

## 📂 Estrutura do Projeto

ClimaBagual/
├── src/
│   └── main/
│       ├── java/
│       │   ├── dal/         # Acesso ao banco de dados (DAO)
│       │   ├── main/        # Classe principal (Main.java)
│       │   ├── model/       # Classes de domínio (Cidade, Estado, Clima)
│       │   ├── services/    # Serviços (API, utilitários, carregadores)
│       │   └── view/        # Interface gráfica (Swing)
│       └── resources/
│           ├── estado-siglas.json
│           └── estados-cidades.json

## 🧪 Como Rodar
1. **Clone o repositório:**

git clone https://github.com/ProjetosUNISC/ClimaBagual

Importe os scripts SQL para seu MySQL:
banco_clima_estado.sql
banco_clima_cidade.sql
banco_clima_clima_atual.sql
banco_clima_clima_dia.sql
banco_clima_clima_hora.sql
Da pasta banco_clima

Compile e execute o projeto com sua IDE favorita (NetBeans, IntelliJ, Eclipse etc.)

🌍 Fonte dos Dados
API Open-Meteo: https://open-meteo.com

Estados e cidades: base oficial IBGE

✅ Créditos
Projeto acadêmico desenvolvido por Mateus Haetinger e Lucas Folharini para a disciplina de Programação Avançada – UNISC (2025).
