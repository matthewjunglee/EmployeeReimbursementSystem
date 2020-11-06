drop table reimbursement_status;
create table reimbursement_status (
	status_id int primary key,
	status text not null
)

select * from reimbursement_status;

insert into reimbursement_status values (0, 'PENDING'), (1, 'APPROVED'), (-1, 'DENIED');
	