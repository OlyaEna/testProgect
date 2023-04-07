create table status
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

create table user
(
    id         bigint not null auto_increment,
    created_at datetime(6),
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    url        varchar(255),
    status_id  bigint,
    primary key (id)
) engine = InnoDB;

alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user
    add constraint FKr62indkt0r2anb0m8hy5ldfpd foreign key (status_id) references status (id);
