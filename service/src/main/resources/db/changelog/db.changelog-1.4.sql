--changeSet dus:1
alter table users
    alter column password set default '{noop}123';
