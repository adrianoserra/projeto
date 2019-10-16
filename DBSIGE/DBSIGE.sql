create database sige;
use sige;

create table participante (
nome varchar(255), 
cpf varchar(14), 
telefone varchar(14), 
senha varchar(255), 
email varchar(255),
setor varchar(255),
tipoUsuario smallint(1),
matricula int unique not null primary key auto_increment
) auto_increment=24006300;

create table evento (
idEvento int not null unique auto_increment primary key,
tema text,
data_evento date,
horario_inicio time,
horario_fim time,
carga_horaria varchar(255),
matricula int,
constraint Palestrante_FK foreign key (matricula) references participante(matricula)
);

create table inscricao (
idInscricao int not null unique auto_increment primary key,
matricula int,
idEvento int,
constraint Participante_FK foreign key (matricula) references participante(matricula),
constraint Evento_FK foreign key (idEvento) references evento(idEvento)
);

create table historico_participante(
idHist int not null unique auto_increment primary key,
presenca varchar(255),
idInscricao int,
matricula int,
constraint ParticipanteHist_FK foreign key (matricula) references participante(matricula),
constraint InscricaoHist_FK foreign key (idInscricao) references inscricao(idInscricao)
);

insert into participante values ('Carlos Henrique', '111.111.111-11', '(81)98116-1613', '12345678', 'carlos@carlos.com', 'STIC', '0', '');
insert into participante values ('George Santos', '222.222.222-22', '(81)98991-8510', '12345678', 'george@george.com', 'STIC', '0', '');
insert into participante values ('Adriano Serra', '333.333.333-33', '(81)99796-1105', '12345678', 'adriano@adriano.com', 'SEFAZ', '0', '');
insert into participante values ('Dayvid Melo', '444.444.444-44', '(81)99863-0434', '12345678', 'dayvid@dayvid.com', 'Nassau', '1', '');
insert into participante values ('Josué Jorge', '555.555.555-55', '(81)98624-4776', '12345678', 'josue@josue.com', 'Accenture', '1', '');

insert into evento values ('', 'Desenvolvimento Web', '2019-10-15', '19:00', '21:00', '5h', '24006301');

insert into inscricao values ('','24006300', '1');
insert into inscricao values ('','24006301', '1');
insert into inscricao values ('','24006302', '1');

insert into historico_participante values ('', 'Presente', '1', '24006301');
insert into historico_participante values ('', 'Ausente', '2', '24006302');
insert into historico_participante values ('', 'Presente', '3', '24006300');


