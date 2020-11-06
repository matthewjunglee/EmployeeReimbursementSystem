drop table users;
create table users (
	user_id serial primary key on delete cascade,
	username text unique not null,
	password text not null,
	first_name text not null,
	last_name text not null,
	email text not null,
	role_id integer references user_roles(user_role_id)
)

insert into users values
	(default, 'johndoe', 'johndoe', 'john', 'doe', 'johndoe@email.com', 1),
	(default, 'janedoe', 'janedoe', 'jane', 'doe', 'janedoe@email.com', 1),
	(default, 'matthewlee', 'matthewlee', 'matthew', 'lee', 'matthewlee@email.com', 2),
	(default, 'bob', 'bee', 'bob', 'bee', 'bobbee@email.com', 2);

select * from users;
