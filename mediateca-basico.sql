create database mediateca;

use mediateca;

/* ---------------------------------------------- 
	CREACION DE TABLAS
---------------------------------------------- */

/* 
tabla: libro

Esta tabla servirá para almacenar informacion
unica y exclusivamente de un libro especifico
*/

create table libro (
	codigo char(8) primary key, -- Ejemplo: LIB00001 
    titulo varchar(100) not null,
    autor varchar(100) not null,
    num_pag int not null,
    editorial varchar(100) not null,
    isbn varchar(100) not null,
    anio_pub int not null,
    unidades_disp int not null    
);

/* 
tabla: revista

Esta tabla servirá para almacenar informacion
unica y exclusivamente de una revista especifica
*/

create table revista (
	codigo char(8) primary key, -- Ejemplo: REV00001 
    titulo varchar(100) not null,
    editorial varchar(100) not null,
    periocidad varchar(100) not null, -- Ejemplo: diaria, semanal, quincenal, mensual
    fecha_pub datetime not null,
    unidades_disp int not null    
);

/* 
tabla: cd

Esta tabla servirá para almacenar informacion
unica y exclusivamente de un cd especifico
*/

create table cd (
	codigo char(8) primary key, -- Ejemplo: CDA00001 
    titulo varchar(100) not null,
    artista varchar(100) not null,
    genero varchar(100) not null, 
    duracion varchar(10) not null, --  Ejemplo: 01:30 (1 hora y 30 min)
    num_canciones int not null,
    unidades_disp int not null    
);

/* 
tabla: dvd

Esta tabla servirá para almacenar informacion
unica y exclusivamente de un dvd especifico
*/

create table dvd (
	codigo char(8) primary key, -- Ejemplo: DVD00001 
    titulo varchar(100) not null,
    director varchar(100) not null,
    duracion varchar(10) not null, --  Ejemplo: 01:30 (1 hora y 30 min)
    genero varchar(100) not null, 
    unidades_disp int not null -- Se le olvido al profe! XD    
);

/*
tabla: rol

Esta tabla tendrá por el momento unicamente 2 roles: ADMIN y USUARIO.
Sin embargo, en un futuro se podrian agregar nuevos roles.
*/

create table rol(
	id_rol int primary key auto_increment,
    nombre varchar(100) not null
);

/*
tabla: usuario

Esta table tendrá informacion del usuario. Recordemos que un usuario
puede tener rol ADMIN o USUARIO. 
*/

create table usuario (
	id_usuario int primary key auto_increment,
    usuario varchar(100) not null,
    password varchar(100) not null,
    id_rol int not null,
    constraint fk_usu_rol foreign key(id_rol) references rol(id_rol)
);

/*
tabla: material

Esta tabla es la que tendrá todos los materiales 
disponibles (Libros, Revistas, CDs y DVDs). 

Como sabremos que tipo de material es?
R/ Por el codigo de 8 digitos: LIB00001, REV00001, etc.

NOTA: Todos estos materiales seran agregados, actualizados o eliminados 
unicamente por el usuario con rol: ADMIN
*/

create table material (
	id_material int primary key auto_increment,
    codigo char(8) not null,
    fecha_creacion datetime not null default now(),
    id_usuario int, 
    constraint fk_mat_usu foreign key(id_usuario) references usuario(id_usuario)
);

select * from material;

/*
tabla: prestamo

Esta tabla es la que tendrá todos prestamos de material que
van realizando.

NOTA: Todos estos materiales seran prestados 
unicamente por el usuario con rol: ADMIN
*/
create table prestamo (
	id_prestamo int primary key auto_increment,
    id_material int not null,
    id_usuario int not null,
    fecha_prestamo datetime not null,
    fecha_devolucion datetime not null,
    constraint fk_pre_mat foreign key(id_material) references material(id_material),
    constraint fk_pre_usu foreign key(id_usuario) references usuario(id_usuario)
);

/* ---------------------------------------------- 
	INSERTS INICIALES
---------------------------------------------- */

insert into rol (nombre) 
values ('ADMINISTRADOR');
insert into rol (nombre) 
values ('BIBLIOTECARIO');
insert into rol (nombre) 
values ('ESTUDIANTE');

insert into usuario (usuario, password, id_rol) 
values ('administrador', 'administrador', 1);