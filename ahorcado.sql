drop database if exists ahorcado;

create database ahorcado;

use ahorcado;

create table if not exists palabras(
	id_palabra int auto_increment,
    palabra varchar (50),
    primary key (id_palabra)
);

insert into palabras (palabra) 
	values ("arbol"), ("delfin"),
	 ("sombrero"),("barra"), ("hervir"),
     ("mision"), ("piscis"), ("pantufla"),
     ("palta"),("yogurt"), ("estuche"),
     ("remera"), ("cuaderno"), ("pizarron"),
     ("matambre"),("estanteria"), ("semaforo"),
     ("cuarentena"),("murcielago"), ("hilo"),
     ("transportista"), ("loro");

create table jugadores(
	id_jugador int auto_increment,
    nombre_jugador varchar(50),
    primary key(id_jugador)
);

create table resultados(
    id_resultado int auto_increment,
    nombre varchar(50),
    palabra varchar(50),
    fecha date,
    primary key(id_resultado)
);

