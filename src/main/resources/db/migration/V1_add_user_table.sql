
create  table Account(
   user_id serial PRIMARY KEY,
   email VARCHAR(50) not null,
   password VARCHAR(50) not null,
   first_name VARCHAR(50) not null,
   last_name VARCHAR(50) not null,
   role VARCHAR(10)
);

