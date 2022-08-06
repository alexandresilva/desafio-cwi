# Desafio Técnico


### Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem

### Promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

### Endpoints criados

- [1] [POST /pauta/v1/cadastrar](http://localhost:8081/pauta/v1/cadastrar)
- [2] [POST /sessao-votacao/v1/abrir-sessao](http://localhost:8081/sessao-votacao/v1/abrir-sessao)
- [3] [POST /sessao-votacao-associado/v1/votar](http://localhost:8081/v1/sessao-votacao-associado/votar)
- [4] [POST /sessao-votacao-associado/v1/resultado/2](http://localhost:8081/sessao-votacao-associado/v1/resultado/2)

***