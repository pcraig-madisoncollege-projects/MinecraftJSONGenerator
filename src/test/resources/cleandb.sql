DELETE FROM `command`;
DELETE FROM `role`;
DELETE FROM `user`;
ALTER TABLE `role` AUTO_INCREMENT = 1;
ALTER TABLE `user` AUTO_INCREMENT = 1;
ALTER TABLE `command` AUTO_INCREMENT = 1;
INSERT INTO `user` VALUES (1,'jsmitty@example.com','password','J. Smitty'),(2,'jdoe@example.com','password1234','Kat'),(3,'almarks@example.com','password4321','Marketh');
INSERT INTO `role` VALUES (1,'admin', 'jsmitty@example.com', 1);
INSERT INTO `command` (id, command, owner_id, shared) VALUES (1,'/tellraw @a [\"Hello, there\"]',1,0),(2,'/tellraw @a [{\"text\":\"This is a test\",\"color\":\"green\"}]',3,1),(3,'/title @a title [{\"text\":\"Hello!\"}]',2,0);