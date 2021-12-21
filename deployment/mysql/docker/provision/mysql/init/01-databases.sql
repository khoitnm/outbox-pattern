CREATE DATABASE IF NOT EXISTS 'outbox_pattern_db';

CREATE SCHEMA `outbox_pattern_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
GRANT ALL ON *.* TO 'root'@'%';