-- A linha abaixo cria um Banco de Dados
CREATE DATABASE dbloja;

-- Cria um usuário com nome de "usuariocurso" e senha "123"
CREATE USER 'loja'@'%' IDENTIFIED BY '123';

GRANT ALL ON *.* TO 'loja'@'%' WITH GRANT OPTION;

flush privileges;

-- A linha abaixo escolhe o Banco de Dados
USE dbloja;

-- A linha abaixo cria uma Tabela
CREATE TABLE tbusuarios(
iduser INT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
fone VARCHAR (15),
login VARCHAR (15) NOT NULL UNIQUE,
senha VARCHAR (15) NOT NULL
);

-- A linha abaixo mostra a Tabela Usuário
describe tbusuarios;
alter table tbusuarios add column perfil varchar(20) not null;

-- A linha abaixo Inseri Dados na Tabela Usuários (CRUD)
INSERT INTO tbusuarios(iduser, nome, fone, login, senha)
VALUES (1,'Marco Antonio','9999-9999','kiko','1234');

-- A linha abaixo exibe os dados da tabela
select * from tbusuarios;

insert into tbusuarios(iduser, nome, fone, login, senha)
values (2,'Rafael','8888-8888','rafa','1234');

insert into tbusuarios(iduser, nome, fone, login, senha)
values (3,'Guilherme','8888-8888','guiamor','1234');

-- A linha abaixo atualiza o Banco de Dados
update tbusuarios set fone='7777-7777' where iduser=3;

-- A linha abaixo apaga um usuário
delete from tbusuarios where iduser=3;

alter table tbclientes add datanascitbclientesmento datetime;

-- Criar uma TABELA de CLIENTES
create table tbclientes(
idcliente int primary key auto_increment,
nomecliente varchar (50) not null,
endcliente varchar (100),
bairrocliente varchar (50) not null,
cidadecliente varchar (50),
estadocliente varchar (2),
cepcliente varchar (10),
fonecliente varchar (15),
emailcliente varchar (20),
dtanivercliente datetime
);

-- A linha abaixo mostra a Tabela Clientes
describe tbclitbclientesentes;

-- A linha abaixo Inseri Dados na Tabela Clientes (CRUD)
insert into tbclientes(nomecliente, endcliente, bairrocliente, cidadecliente, estadocliente, cepcliente, fonecliente, emailcliente, dtanivercliente  )
values ('Antonio Pinto de Moura','Rua Padre Hugo Greco, 200','Nova Lorena','Lorena','SP','12-600-000','(12)3152-6306','pintinho@uol.com.br','13/12/1945');

select * from tbclientes;

CREATE TABLE tbos (
os INT PRIMARY KEY auto_increment, 
data_os TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
equipamento VARCHAR(150) NOT NULL,
defeito VARCHAR(150) NOT NULL,tbclientes
servico VARCHAR(150),
tecnico VARCHAR(30),
valor DECIMAL(10,2),
idcliente INT NOT NULL
);


