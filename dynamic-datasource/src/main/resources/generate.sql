-- auto Generated on 2025-02-06
-- DROP TABLE IF EXISTS student;
CREATE TABLE student(
                        id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
                        age INT (11) NOT NULL DEFAULT -1 COMMENT 'age',
                        create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
                        update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
                        PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'student';


-- auto Generated on 2025-02-06
-- DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher(
                        id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
                        age INT (11) NOT NULL DEFAULT -1 COMMENT 'age',
                        create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
                        update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
                        PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'teacher';
