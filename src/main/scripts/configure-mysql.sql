# #Use to run mysql db docker image, optional if you are not using the local mysql
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
# create Databases


-- Create database service accounts
CREATE USER 'sfg_dev_user'@'localhost' INDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'localhost' INDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_user'@'%' INDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'%' INDENTIFIED BY 'guru';

#Database grants

GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_prod.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_dev_user'@'%';