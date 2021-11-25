create database CreditDB; -- Creates the new database
create user 'credit-app-user'@'%' identified by 'credit-password'; -- Creates new user for use with spring application
grant all on CreditDB.* to 'credit-app-user'@'%'; -- Gives all privileges to the new user on the newly created database

create database ProductDB; -- Creates the new database
create user 'product-app-user'@'%' identified by 'product-password'; -- Creates new user for use with spring application
grant all on ProductDB.* to 'product-app-user'@'%'; -- Gives all privileges to the new user on the newly created database

create database CustomerDB; -- Creates the new database
create user 'customer-app-user'@'%' identified by 'customer-password'; -- Creates new user for use with spring application
grant all on CustomerDB.* to 'customer-app-user'@'%'; -- Gives all privileges to the new user on the newly created database