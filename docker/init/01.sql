CREATE DATABASE IF NOT EXISTS `ecommerce-log`;
CREATE DATABASE IF NOT EXISTS `ecommerce-order`;

GRANT ALL ON `ecommerce-log`.* TO 'user'@'%';
GRANT ALL ON `ecommerce-order`.* TO 'user'@'%';

