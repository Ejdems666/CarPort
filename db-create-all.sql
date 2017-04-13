create table user (
  id                            integer auto_increment not null,
  name                          varchar(255),
  surname                       varchar(255),
  created_at                    date,
  status                        integer,
  type                          integer,
  email                         varchar(255),
  password                      varchar(255),
  salt                          varchar(255),
  balance                       integer,
  constraint pk_user primary key (id)
);

