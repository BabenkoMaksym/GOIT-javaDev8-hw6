create table public.worker (
    id serial primary key,
    first_name varchar(1000) not null check (length(first_name) >= 2),
    last_name varchar(1000),
    birthday date check (birthday > '1900-01-01'),
    level varchar not null check (level = 'Trainee' or level = 'Junior' or level = 'Middle' or level = 'Senior'),
    salary int check (salary >= 100 and salary <=100000)
);

create table public.client (
    id serial primary key,
    client_name varchar(1000) not null check (length(client_name) >= 2)
);

create table "public".project (
    id serial primary key,
    client_id int  references "public".client("id"),
    start_date date check (start_date > '1990-01-01'),
    finish_date date check (finish_date >= start_date)
);

create table public.project_worker (
    project_id int,
    worker_id int,
    constraint fk_project_worker
        foreign key (project_id)
        references public.project(id),
        foreign key (worker_id)
        references public.worker(id)
);
