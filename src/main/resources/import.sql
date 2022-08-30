insert into PAUTA (id, descricao, data_cadastro) values (10, 'Aumento salarial para diretores', now());
insert into PAUTA (id, descricao, data_cadastro) values (11, 'Pauta para novo cargo', now());
insert into PAUTA (id, descricao, data_cadastro) values (12, 'Expulsão de colaborador', now());

insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (10, 'Alexandre Oliveira', 'alexandre@gmail.com', '29834198000', true, now());
insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (11, 'Suzana Oliveira', 'suzana@gmail.com', '54502020087', true, now());

insert into SESSAO_VOTACAO (id, descricao, data_cadastro, id_pauta, tempo_abertura) values (1, 'Sessão para votação de aumento salarial', now(), 10, 30);
insert into SESSAO_VOTACAO (id, descricao, data_cadastro, id_pauta, tempo_abertura) values (2, 'Sessão para criação de novo cargo', now(), 11, 30);
insert into SESSAO_VOTACAO (id, descricao, data_cadastro, id_pauta, tempo_abertura) values (3, 'Sessão para expulsão de colaborador', now(), 12, 30);

CREATE OR REPLACE VIEW vw_votacao AS SELECT p.id, p.descricao AS nome_pauta, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = true) as sim, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = false) as nao, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id) as total FROM SESSAO_VOTACAO sv JOIN PAUTA p ON (sv.id_pauta = p.id);

--Senha padrao 123 para todos os usuarios