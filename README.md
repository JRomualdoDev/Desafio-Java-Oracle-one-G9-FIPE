
#  Tabela FIPE - Consulta de Veículos 🚗

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

## 🎯 Sobre o Projeto

Este projeto é uma aplicação de console (CLI) desenvolvida em Java com o framework Spring Boot. O objetivo é consumir a API pública da Tabela FIPE para consultar preços de veículos de forma rápida e interativa. O usuário pode navegar entre tipos de veículos, marcas, modelos e anos para obter informações detalhadas sobre o valor de mercado.

A aplicação foi construída para demonstrar habilidades em consumo de APIs, manipulação de dados (JSON), e estruturação de um projeto Java moderno com Spring Boot, focando em boas práticas e código limpo.

## ✨ Funcionalidades Principais

- **Consulta por Tipo de Veículo:** Permite ao usuário escolher entre Carros, Motos e Caminhões.
- **Busca Dinâmica de Marcas e Modelos:** Carrega as marcas e modelos correspondentes ao tipo de veículo selecionado.
- **Avaliação por Ano:** Exibe uma lista de todos os anos disponíveis para um modelo específico, com seus respectivos valores, tipo de combustível e código FIPE.
- **Interface de Console Interativa:** Um menu guiado para facilitar a navegação e a experiência do usuário.
- **Consumo de API Externa:** Integração com a API da Tabela FIPE para obtenção de dados em tempo real.
- **Conversão de Dados:** Utiliza a biblioteca Jackson para desserializar o JSON da API em objetos Java de forma eficiente.

## 🧠 Aprendizados

Este projeto solidificou conhecimentos essenciais no ecossistema Java e Spring. Os principais aprendizados foram:

-   **Manipulação de Coleções com Streams e Lambdas:** Utilização da API de Streams do Java 8+ para realizar operações de filtragem, mapeamento e busca em listas de dados de forma declarativa e eficiente. Isso foi crucial para, por exemplo, filtrar modelos de veículos por nome a partir da lista completa retornada pela API.

-   **Desserialização de JSON com Jackson:** Mapeamento automático de respostas JSON complexas (incluindo listas e objetos aninhados) para modelos de dados (Records e Classes) em Java, facilitando enormemente a manipulação dos dados.

## 🚀 Como Executar

Para executar este projeto, você precisará ter o Java 17 e o Maven instalados em sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd FIPE
    ```

3.  **Execute a aplicação com o Maven:**
    O Spring Boot cuidará de todo o resto!
    ```bash
    mvn spring-boot:run
    ```

4.  **Pronto!** O menu interativo aparecerá no seu terminal para você começar a consultar.

## 📊 Diagrama de Fluxo

O diagrama abaixo ilustra o fluxo de interação do usuário com a aplicação.

```mermaid
graph TD;
    A[Início] --> B{Escolha o tipo de veículo<br>(Carro, Moto, Caminhão)};
    B --> C[API: Busca Marcas];
    C --> D{Escolha a marca};
    D --> E[API: Busca Modelos];
    E --> F{Filtre pelo nome do modelo};
    F --> G[API: Busca Anos/Versões do Modelo];
    G --> H[Processa todos os anos];
    H --> I[API: Busca dados detalhados para cada ano];
    I --> J[Exibe tabela com<br>Ano, Combustível e Valor];
    J --> K[Fim];
```

---
*Desenvolvido  por José Romualdo Junior*
