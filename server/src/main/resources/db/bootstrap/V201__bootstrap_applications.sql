--
-- Dumping data for table `workspaces`
--

LOCK TABLES `workspaces` WRITE;
/*!40000 ALTER TABLE `workspaces` DISABLE KEYS */;
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (21,'WebApplication','Add, Delete or Update multiple workspace versions using Live.','Web workspace (Live)', false, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (22,'IOSNative','Add, Delete or Update multiple workspace versions using Live.','iOS app (Live)', false, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (23,'AndroidNative','Add, Delete or Update multiple workspace versions using Live.','Android app (Live)', false, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (24,'MobileWeb','Add, Delete or Update multiple workspace versions using Live.','Responsive Web (Live)', false, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (25,'WebApplication','Mita Simply travel is demo site. Here we will be showing how to achieve web testing with Mita.','Web workspace (Demo)', true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (29,'MobileWeb','Mita Simply travel is demo site. Here we will be showing how to achieve responsive web testing with Mita.','Responsive Web (Demo)', true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (30,'AndroidNative','Demos how Mita simplifies Android Native Workspace automation.','Android app (Demo)', true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO `workspaces` (`id`, `type`, `description`, `name`, `is_demo`, `created_date`, `updated_date`) VALUES (31,'IOSNative','Demos how Mita simplifies iOS Native Workspace automation.','iOS app (Demo)', true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
/*!40000 ALTER TABLE `workspaces` ENABLE KEYS */;
UNLOCK TABLES;
