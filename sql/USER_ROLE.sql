drop table user_roles;
create table user_roles (
	user_role_id serial primary key,
	user_role text not null
)

insert into user_roles values (1, 'finance_manager'), (2, 'employee');

select * from user_roles;