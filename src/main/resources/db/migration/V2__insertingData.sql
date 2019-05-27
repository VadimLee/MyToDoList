
INSERT INTO `to_do_list`.`users` 
VALUES 
('admin','{noop}123',1),
('employee','{noop}123',1);

INSERT INTO `to_do_list`.`authorities` 
VALUES 
('employee','ROLE_EMPLOYEE'),
('admin','ROLE_EMPLOYEE'),
('admin','ROLE_ADMIN');

INSERT INTO `to_do_list`.`tasks`

VALUES
(1, 'monday task', 1, 'monday description', false),
(2, 'tuesday task', 2, 'tuesday description', false),
(3, 'wednesday task', 3, 'wednesday description', false),
(4, 'thursday task', 4, 'thursday description', false),
(5, 'friday task', 5, 'friday description', false),
(6, 'saturday task', 6, 'saturday description', false),
(7, 'sunday task', 0, 'sunday description', false);


