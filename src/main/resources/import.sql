insert ignore into associacao (descricao) values ('Sicred-PR');
insert ignore into associacao (descricao) values ('Sicred-MG');
insert ignore into associacao (descricao) values ('Sicred-SP');
insert ignore into associacao (descricao) values ('Sicred-RS');
insert ignore into associacao (descricao) values ('Sicred-RJ');

INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Andreia Lara Monteiro', '1', '96478702308');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Miguel Davi da Rocha', '2', '12717312587');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Bianca Lara Corte Real', '3', '89235209420');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Yago Tomás Pires', '1', '94277649459');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Antonio Severino Teixeira', '2', '70506294641');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Cláudio Ruan Brito', '3', '0164693841');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Mário Kevin Cláudio Alves', '4', '26971278950');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Arthur Leandro Gonçalves', '5', '06263533277');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Benjamin Roberto Pires', '4', '08701435906');
INSERT ignore INTO associado (nome, associacao_id, cpf) VALUES ('Bárbara Regina Baptista', '5', '32548118282');

INSERT ignore INTO pauta (descricao) VALUES ('Pauta: Agornegocios Tema: Taxa de Juros');
INSERT ignore INTO pauta (descricao) VALUES ('Pauta: Industria Tema: Geracao de empregos');
INSERT ignore INTO pauta (descricao) VALUES ('Pauta: Brasil Tema: Desafioos economicos ');
INSERT ignore INTO pauta (descricao) VALUES ('Pauta: Cooperativismo Tema: Aceitacao de novos cooperados');

INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('1', utc_timestamp , '1', utc_timestamp ,utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('1', utc_timestamp , '2', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('1', utc_timestamp , '3', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('1', utc_timestamp , '4', utc_timestamp, utc_timestamp);

INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('2', utc_timestamp , '1', utc_timestamp ,utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('2', utc_timestamp , '2', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('2', utc_timestamp , '3', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('2', utc_timestamp , '4', utc_timestamp, utc_timestamp);

INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('3', utc_timestamp , '1', utc_timestamp ,utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('3', utc_timestamp , '2', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('3', utc_timestamp , '3', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('3', utc_timestamp , '4', utc_timestamp, utc_timestamp);

INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('4', utc_timestamp , '1', utc_timestamp ,utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('4', utc_timestamp , '2', utc_timestamp ,utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('4', utc_timestamp , '3', utc_timestamp, utc_timestamp);
INSERT ignore INTO sessao(associacao_id, inicio_sessao, pauta_id, data_sessao, termino_sessao) VALUES ('4', utc_timestamp , '4', utc_timestamp, utc_timestamp);
       
INSERT ignore INTO voto (associado_id, pauta_id, favoravel, sessao_id) VALUES (1, 1, 0, 1);
INSERT ignore INTO voto (associado_id, pauta_id, favoravel, sessao_id) VALUES (4, 1, 0, 1);