insert into SESSAO_VOTACAO (id, data_cadastro, data_final_sessao) values (10, now(), DATEADD(minute,30,now()));
insert into SESSAO_VOTACAO (id, data_cadastro, data_final_sessao) values (11, now(), DATEADD(minute,30,now()));
insert into SESSAO_VOTACAO (id, data_cadastro, data_final_sessao) values (12, now(), DATEADD(minute,30,now()));

insert into PAUTA (id, descricao, data_cadastro, sessao_votacao_id) values (10, 'Aumento salarial para diretores', now(), 10);
insert into PAUTA (id, descricao, data_cadastro, sessao_votacao_id) values (11, 'Pauta para novo cargo', now(), 11);
insert into PAUTA (id, descricao, data_cadastro, sessao_votacao_id) values (12, 'Expulsão de colaborador', now(), 12);

insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (10, 'Alexandre Oliveira', 'alexandre@gmail.com', '29834198000', true, now());
insert into ASSOCIADO (id, nome, email, cpf, apto, data_cadastro) values (11, 'Suzana Oliveira', 'suzana@gmail.com', '54502020087', true, now());

CREATE OR REPLACE VIEW vw_votacao AS SELECT p.id, p.descricao as nome_pauta, (SELECT COUNT(*) FROM VOTO v WHERE v.PAUTA_ID = p.ID AND v.VOTO = true) as sim, (SELECT COUNT(*) FROM VOTO v WHERE v.PAUTA_ID = p.ID AND v.VOTO = false) as nao, (SELECT COUNT(*) FROM VOTO v WHERE v.PAUTA_ID = p.ID) as total FROM  PAUTA p;

--Senha padrao 123 para todos os usuarios