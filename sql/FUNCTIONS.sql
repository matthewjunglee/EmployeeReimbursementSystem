-- Don't add to project1 folder

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

select * from get_reimbursements();

-- view all reimbursements by id
create or replace function get_reimbursements_by_id(author_id integer) returns
table(id int, first_name text, last_name text, amount numeric, submitted timestamp,
		resolved timestamp, description text, receipt bytea, status_id int, type_id int)
as $$
	begin
		return query select r.id, u.first_name, u.last_name, r.amount, r.submitted, r.resolved,
							r.description, r.receipt, r.status_id, r.type_id from reimbursement r 
						join users u on r.author = author_id and r.author = u.user_id order by r.id;
	end
$$ language plpgsql;
select * from get_reimbursements_by_id(3);