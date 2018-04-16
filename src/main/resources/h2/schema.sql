------------------------------- Dossier schema.sql

create sequence DS_GENERATE_ID minvalue 1 start with 1 increment by 1 cache 1;


create table ds_simple_entity 
(
	simple_id number(10) primary key,	
	simple_description varchar2(1000) not null
)