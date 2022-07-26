# Desafio Técnico CWI


## Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem

## Promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

## Clonando o Projeto
Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```
cd "diretorio de sua preferencia"
git clone https://github.com/alexandresilva/desafio-cwi.git
```

## Build
### Via Maven
Para construir o projeto com o Maven, executar os comando abaixo:

Executar o comando:  

```
mvn clean package install
```
O comando acima irá baixar todas as dependências do projeto e criar um diretório **target** com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

### Configurações do Java
Este projeto está rodando com a versão 1.8

## Swagger
- [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

## Endpoints criados

- [1] [POST /pauta/v1/cadastrar](http://localhost:8081/pauta/v1/cadastrar)
- [2] [POST /sessao-votacao/v1/abrir-sessao](http://localhost:8081/sessao-votacao/v1/abrir-sessao)
- [3] [POST /associado/v1/votar](http://localhost:8081/sessao-votacao-associado/v1/votar)
- [4] [POST /associado/v1/resultado/2](http://localhost:8081/sessao-votacao-associado/v1/resultado/2)
- [5] [GET /associado/v1/listarTodos](http://localhost:8081/associado/v1/listarTodos)
- [6] [GET /associado/v1/listarPorId/1](http://localhost:8081/associado/v1/listarPorId/1)

## Exemplos de uso no Postman

- [1] Cadastrar Pauta:
```
{
    "descricao": "Pauta para votação do aumento salarial",
    "sessaoVotacao": {
        "id": 1,
        "descricao": "Sessão para votação de aumento salarial",
        "dataCadastro": "2022-08-31T21:10:00",
        "tempoAbertura": 30
    }
}
```
- [2] Abrir Sessão de Votação:
```
{
    "descricao": "Sessão para votação da Pauta 1",
    "dataCadastro": "2022-08-05T14:36:22",
    "idPauta": 1,
    "tempoAbertura": 30
}
```
- [3] Votar: 
```
{
    "pauta":
        {
            "id": 12,
            "descricao": "Aumento salarial para diretores",
            "dataCadastro": "2022-09-01T10:00:00",
            "sessaoVotacao": {
                "id": 1,
                "descricao": "Sessão para votação de aumento salarial",
                "dataCadastro": "2022-08-31T21:10:00",
                "tempoAbertura": 30
            }
        },
    "associado": 
        {
            "id": "11",
            "nome": "Suzana",
            "email": "alexandre@gmail.com",
            "cpf": "29834198000",
            "apto": true,
            "dataCadastro": "2022-08-01T10:00:00"
        },
    "voto": true,
    "dataCadastro": "2022-08-01T10:00:00"
}
```
- [4] Ver Resultado:
```
http://localhost:8081/votacao/v1/resultado/11
```

***