insert into customer(full_name, email, password) values ('Afonso Silva', 'afonso@email.com', 'afonso777');

insert into reservation(occupation_date, occupation_time, qnt_people, status, total) values('2024-05-30', '14:30:00', 1, 'FINISHED', 30);

insert into customer_reservations(customer_id, reservations_id) values (1, 1);