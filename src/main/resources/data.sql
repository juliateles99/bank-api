INSERT INTO conta (id, numero, titular, saldo) VALUES (1, '0001', 'Júlia Teles', 1000.00);
INSERT INTO conta (id, numero, titular, saldo) VALUES (2, '0002', 'Maria Souza', 500.00);
INSERT INTO conta (id, numero, titular, saldo) VALUES (3, '0003', 'Carlos Silva', 1500.00);

INSERT INTO transacao (id, valor, data, tipo, conta_id, descricao)
VALUES (1, 1000.00, NOW(), 'DEPOSITO', 1, 'Depósito inicial');

INSERT INTO transacao (id, valor, data, tipo, conta_id, descricao)
VALUES (2, 500.00, NOW(), 'DEPOSITO', 2, 'Depósito inicial');

INSERT INTO transacao (id, valor, data, tipo, conta_id, descricao)
VALUES (3, 1500.00, NOW(), 'DEPOSITO', 3, 'Depósito inicial');