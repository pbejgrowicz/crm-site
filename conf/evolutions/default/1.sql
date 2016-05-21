# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  number                    varchar(255),
  city                      varchar(255),
  company                   varchar(255),
  user_email                varchar(255),
  constraint pk_contact primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  is_super_user             boolean,
  constraint pk_user primary key (email))
;

create sequence contact_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contact;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contact_seq;

drop sequence if exists user_seq;

