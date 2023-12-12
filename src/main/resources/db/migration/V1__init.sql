create table customer (
    id              bigserial       primary key not null,
    document        varchar(255)    not null,
    salary          numeric(15,4)   not null,
    birthday        date            not null,
    name            varchar(255)    not null,
    constraint document_uk unique (document)
);

create table loan (
    id              bigserial       primary key not null,
    code            varchar(255)    not null,
    request_date    timestamp       not null,
    status          varchar(30)     not null,
    value           numeric(15,4)   not null,
    customer_id     bigserial       not null,
    constraint code_uk unique (code),
    constraint customer_fk foreign key (customer_id) references customer (id)
);

create table suggestion (
    id              bigserial       primary key not null,
    fees            numeric(15,4)   not null,
    installments    integer         not null,
    end_value       numeric(15,4)   not null,
    loan_id         bigserial       not null,
    constraint suggestion_loan_fk foreign key (loan_id) references loan (id)
);


create table risk(
   id               bigserial        primary key not null,
   loan             varchar(255)    not null,
   committed_value  numeric(15,4),
   last_date        timestamp,
   status           varchar(15) not null
);