--create schema expense_reimbursement_system;
--create user ers_user with password 'project1';

drop table reimbursement;
drop table reimbursement_type;
drop table reimbursement_status;
drop table users;
drop table user_roles;


-- TYPE
create table reimbursement_type (
	type_id int primary key,
	type text not null
);
insert into reimbursement_type values 
	(1, 'LODGING'), (2, 'TRAVEL'), (3, 'FOOD'), (4, 'OTHER');

-- STATUS
create table reimbursement_status (
	status_id int primary key,
	status text not null
);
insert into reimbursement_status values (0, 'PENDING'), (1, 'APPROVED'), (-1, 'DENIED');

-- ROLES
create table user_roles (
	user_role_id serial primary key,
	user_role text not null
);
insert into user_roles values (1, 'finance_manager'), (2, 'employee');

-- USERS
create table users (
	user_id serial primary key,
	username text unique not null,
	password text not null,
	first_name text not null,
	last_name text not null,
	email text not null,
	role_id integer references user_roles(user_role_id)
);

-- REIMBURSEMENT
create table reimbursement (
	id serial primary key,
	amount numeric not null,
	submitted timestamp not null,
	resolved timestamp default(null),
	description text not null,
	receipt bytea default(null),
	
	author int references users(user_id),
	resolver int default(null) references users(user_id),
	status_id int references reimbursement_status(status_id),
	type_id int references reimbursement_type	
);

-- function to hash passwords and to work with trigger
create or replace function hashPassword()
returns trigger
as $$
	begin
		if(new.password=old.password) then
			return new;
		end if;
		new.password:= md5(new.username||new.password||'fragile');
		return new;
	end;
$$ language plpgsql;

-- create the trigger that will call the function when needed
create trigger hashPass
before insert or update on users
for each row
execute function hashPassword();


insert into users values
	(default, 'johndoe', 'johndoe', 'john', 'doe', 'johndoe@email.com', 1),
	(default, 'janedoe', 'janedoe', 'jane', 'doe', 'janedoe@email.com', 1),
	(default, 'matthewlee', 'matthewlee', 'matthew', 'lee', 'matthewlee@email.com', 2),
	(default, 'bob', 'bee', 'bob', 'bee', 'bobbee@email.com', 2),
	(default, 'tee', 'tee', 'tee', 'tee', 'tee@email.com', 2),
	(default, 'delete', 'delete', 'delete', 'delete', 'delete@email.com', 2);

insert into reimbursement values 
	(default, 20.00, '10/14/2020 13:24:00', null, 'purchased food at company picnic', null, 3, null, 0, 3),
	(default, 40.00, '10/15/2020 11:59:56', null, 'traveled to conference 50 miles away', null, 3, null, 0, 2),
	(default, 100.00, '10/16/2020 08:00:34', null, 'stayed in hotel for business meeting', null, 4, null, 0, 1),
	(default, 70.00, '10/17/2020 17:23:12', null, 'replaced company car keys that I lost', null, 4, null, 0, 4),
	(default, 80.00, '10/18/2020 07:32:21', null, 'replaced company car keys that I lost', null, 3, null, -1, 4),
	(default, 60.00, '10/18/2020 10:37:43', null, 'purchased office supplies', null, 4, null, 1, 4);

-------------------------------------------------
-- USERS
-- delete user
create or replace procedure delete_user(userid integer) language sql as $$
	delete from reimbursement r where r.author = userid;
	delete from users u where u.user_id = userid;
$$;

-- login
create or replace function login(uname text, pword text) returns
table(id int, u_name text, f_name text, l_name text, email text, role int) as $$
	declare
		hash_password text;
	begin
		hash_password:= md5(uname||pword||'fragile');
		return query select u.user_id, u.username, u.first_name, u.last_name, u.email, u.role_id
				from users u where u.username = uname AND u."password" = hash_password;
	end
$$ language plpgsql;

----------------------------------------------
-- REIMBURSEMENTS
-- view all reimbursements
create or replace function get_reimbursements() returns
table(id int, first_name text, last_name text, amount numeric, submitted timestamp,
		resolved timestamp, description text, receipt bytea, status_id int, type_id int)
as $$
	begin
		return query select r.id, u.first_name, u.last_name, r.amount, r.submitted, r.resolved,
							r.description, r.receipt, r.status_id, r.type_id from reimbursement r 
						join users u on r.author = u.user_id order by r.id;
	end
$$ language plpgsql;

create or replace function get_reimbursement_by_id(reimb_id integer) returns
table(id int, first_name text, last_name text, amount numeric, submitted timestamp,
		resolved timestamp, description text, receipt bytea, status_id int, type_id int)
as $$
	begin
		return query select r.id, u.first_name, u.last_name, r.amount, r.submitted, r.resolved,
							r.description, r.receipt, r.status_id, r.type_id from reimbursement r 
						join users u on r.id = reimb_id and r.author = u.user_id order by r.id;
	end
$$ language plpgsql;

-- view all reimbursements by id
create or replace function get_reimbursements_by_author_id(author_id integer) returns
table(id int, first_name text, last_name text, amount numeric, submitted timestamp,
		resolved timestamp, description text, receipt bytea, status_id int, type_id int)
as $$
	begin
		return query select r.id, u.first_name, u.last_name, r.amount, r.submitted, r.resolved,
							r.description, r.receipt, r.status_id, r.type_id from reimbursement r 
						join users u on r.author = author_id and r.author = u.user_id order by r.id;
	end
$$ language plpgsql;

--select * from users order by user_id;
--select * from reimbursement order by id;
--select * from get_reimbursement_by_id(3);
--select * from get_reimbursements_by_author_id(3);