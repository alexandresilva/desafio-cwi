insert into PAUTA (id, descricao, data_cadastro) values (1, 'Aumento salarial para diretores', now());
insert into PAUTA (id, descricao, data_cadastro) values (2, 'Pauta para novo cargo', now());

insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (1, 'Alexandre Oliveira', 'alexandre@gmail.com', '00011122299', true, now());
insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (2, 'Suzana Oliveira', 'suzana@gmail.com', '00011122288', true, now());

insert into SESSAO_VOTACAO (id, descricao, data_cadastro, id_pauta, tempo_abertura) values (1, 'Sessão para votação de aumento salarial', now(), 1, 30);
insert into SESSAO_VOTACAO (id, descricao, data_cadastro, id_pauta, tempo_abertura) values (2, 'Sessão para criação de novo cargo', now(), 2, 30);

insert into SESSAO_VOTACAO_ASSOCIADO (id, id_sessao_votacao, id_associado, voto, data_cadastro) values (1, 1, 1, true, now());
insert into SESSAO_VOTACAO_ASSOCIADO (id, id_sessao_votacao, id_associado, voto, data_cadastro) values (2, 1, 2, false, now());
insert into SESSAO_VOTACAO_ASSOCIADO (id, id_sessao_votacao, id_associado, voto, data_cadastro) values (3, 2, 1, true, now());
insert into SESSAO_VOTACAO_ASSOCIADO (id, id_sessao_votacao, id_associado, voto, data_cadastro) values (4, 2, 2, false, now());

CREATE OR REPLACE VIEW vw_votacao AS SELECT p.id, p.descricao AS pauta, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = true) as sim, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = false) as nao, (SELECT COUNT(*) as totalVotos FROM SESSAO_VOTACAO_ASSOCIADO  sva2 JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID WHERE ID_SESSAO_VOTACAO = sv.id) as total FROM SESSAO_VOTACAO sv JOIN PAUTA p ON (sv.id_pauta = p.id);

--Senha padrao 123 para todos os usuarios