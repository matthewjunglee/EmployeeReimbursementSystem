drop table reimbursement;
create table reimbursement (
	id serial primary key,
	amount numeric not null,
	submitted timestamp not null,
	resolved timestamp default(null),
	description text not null,
	receipt bytea default(null),
	
	author int references users(user_id),
	resolver int default(null) references users(user_id),
	status_id int default(0) references reimbursement_status(status_id),
	type_id int references reimbursement_type	
);

insert into reimbursement values 
	(default, 20.00, '10/14/2020 13:24:00', null, 'purchased food at company picnic', null, 3, null, 0, 3),
	(default, 40.00, '10/15/2020 11:59:56', null, 'traveled to conference 50 miles away', null, 3, null, 0, 2),
	(default, 100.00, '10/16/2020 08:00:34', null, 'stayed in hotel for business meeting', null, 4, null, 0, 1),
	(default, 70.00, '10/17/2020 17:23:12', null, 'replaced company car keys that I lost', null, 4, null, 0, 4),
	(default, 80.00, '10/18/2020 07:32:21', '10/18/2020 10:37:43', 'replaced company car keys that I lost', null, 3, null, -1, 4),
	(default, 60.00, '10/18/2020 10:37:43', '10/19/2020 12:34:25', 'purchased office supplies', null, 4, null, 1, 4);
	
select * from reimbursement;
select * from users;

insert into reimbursement values 
	(default, 20.00, '10/14/2020 13:24:00', null, 'purchased food at company picnic', null, 3, null, 0, 3);