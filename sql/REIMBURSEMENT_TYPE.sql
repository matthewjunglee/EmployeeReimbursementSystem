drop table reimbursement_type;
create table reimbursement_type (
	type_id int primary key,
	type text not null
)

select * from reimbursement_type;

insert into reimbursement_type values 
	(1, 'LODGING'), (2, 'TRAVEL'), (3, 'FOOD'), (4, 'OTHER');