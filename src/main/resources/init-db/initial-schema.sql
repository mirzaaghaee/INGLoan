
create table if not exists loan
(
    id                      bigint         not null
    primary key,
    amount                  decimal(38, 2) null,
    customer_full_name      varchar(255)   null,
    customer_id             int            null,
    reference_no            varchar(255)   null,
    registration_time_stamp datetime(6)    null
    );

create table if not exists loan_seq
(
    next_val bigint null
);