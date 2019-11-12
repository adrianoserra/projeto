create database SIGE;
use SIGE;

create table participante (
senha varchar(255),
nome varchar(255), 
cpf varchar(14) unique not null primary key,
telefone varchar(14),
email varchar(255),
setor varchar(255),
tipoUsuario smallint(1) default '1'
);

create table evento (
idEvento int not null unique auto_increment primary key,
tema text,
data_evento varchar(10),
horario_inicio varchar(5),
horario_fim varchar(5),
carga_horaria varchar(255),
palestrante varchar(14),
constraint Palestrante_FK foreign key (palestrante) references participante(cpf)
);

create table inscricao (
idInscricao int not null unique auto_increment primary key,
cpf varchar(14),
idEvento int
);

create table historico_participante(
idHist int not null unique auto_increment primary key,
presenca varchar(255),
idInscricao int,
cpf varchar(14),
constraint ParticipanteHist_FK foreign key (cpf) references participante(cpf),
constraint InscricaoHist_FK foreign key (idInscricao) references inscricao(idInscricao)
);

insert into participante values ('12345678', 'Carlos', '111.111.111-11','(81)99999-9999', 'carlos@sige.com', 'SEMIC', '0');
insert into participante (senha, nome, cpf, telefone, email, setor) values ('12345678', 'George', '222.222.222-22','(81)99999-9999', 'george@sige.com', 'SEDES');
insert into participante (senha, nome, cpf, telefone, email, setor) values ('12345678', 'George', '333.333.333-33','(81)99999-9999', 'adriano@sige.com', 'SEFAZ');
select * from participante;

insert into evento values ('','Banco de Dados Relacional', '27/10/2019', '19:00', '21:00', '5 horas', '222.222.222-22');
insert into evento values ('','Banco de Dados Orientado a Grafos', '06/10/2019', '19:00', '21:00', '5 horas', '111.111.111-11');
select * from evento;

insert into inscricao values('', '111.111.111-1', '2');
insert into inscricao values('', '222.222.222-22', '3');
select * from inscricao;

insert into historico_participante values ('', 'Presente', '1', '111.111.111-11');
insert into historico_participante values ('', 'Ausente', '2', '222.222.222-22');
select * from historico_participante;

select participante.nome as Participante, evento.tema as Evento, evento.data_evento as Data 
from inscricao inner join participante on inscricao.cpf = participante.cpf inner join evento on inscricao.idEvento = evento.IdEvento;

select participante.nome as Participante, historico_participante.presenca as Presença  
from historico_participante inner join participante on historico_participante.cpf = participante.cpf;

Alter table evento add column descricao varchar(100);
