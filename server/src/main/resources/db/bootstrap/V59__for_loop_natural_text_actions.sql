ALTER TABLE `natural_text_actions` ADD COLUMN `loop_type` varchar(1000) default NULL;

INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (1107, 'WebApplication', 'SET_NAME', 'Loop over data set in ${test-data-profile} where set name ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1096, 10210, 20164, 30193, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (1108, 'WebApplication', 'INDEX', 'Loop over data set in ${test-data-profile} from index ${left-data} to index ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"start\", \"right-data\":\"end\"}}', 'forloop', NULL, 'forloop', 1097, 10211, 20165, 30194, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (1109, 'WebApplication', 'INDEX', 'Loop over data set in ${test-data-profile} from index start to index end', '{\"test-data\":{\"test-data-profile\":\"test data profile\"}}', 'forloop', NULL, 'forloop', 1098, 10212, 20166, 30195, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (1110, 'WebApplication', 'PARAMETER_VALUE', 'Loop over data set in ${test-data-profile} when ${left-data} value ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"parameter\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1099, 10226, 20176, 30196, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (1111, 'WebApplication', 'PARAMETER_VALUE', 'Loop over data set ${test-data-profile} when ${left-data} value ${operator}','{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"isEmpty/isNotEmpty\", \"left-data\":\"parameter\"}}', 'forloop', NULL, 'forloop', 1111, 10213, 20167, 30197, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"isEmpty\", \"isNotEmpty\"]', 'FOR_LOOP');


INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (10223, 'MobileWeb', 'SET_NAME', 'Loop over data set in ${test-data-profile} where set name ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1096, 10210, 20164, 30193, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (10224, 'MobileWeb', 'INDEX', 'Loop over data set in ${test-data-profile} from index ${left-data} to index ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"start\", \"right-data\":\"end\"}}', 'forloop', NULL, 'forloop', 1097, 10211, 20165, 30194, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (10225, 'MobileWeb', 'INDEX', 'Loop over data set in ${test-data-profile} from index start to index end', '{\"test-data\":{\"test-data-profile\":\"test data profile\"}}', 'forloop', NULL, 'forloop', 1098, 10212, 20166, 30195, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (10226, 'MobileWeb', 'PARAMETER_VALUE', 'Loop over data set in ${test-data-profile} when ${left-data} value ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"parameter\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1099, 10226, 20176, 30196, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (10227, 'MobileWeb', 'PARAMETER_VALUE', 'Loop over data set ${test-data-profile} when ${left-data} value ${operator}','{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"isEmpty/isNotEmpty\", \"left-data\":\"parameter\"}}', 'forloop', NULL, 'forloop', 1111, 10227, 20167, 30197, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"isEmpty\", \"isNotEmpty\"]', 'FOR_LOOP');

INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (20173, 'AndroidNative', 'SET_NAME', 'Loop over data set in ${test-data-profile} where set name ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1096, 10210, 20164, 30193, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (20174, 'AndroidNative', 'INDEX', 'Loop over data set in ${test-data-profile} from index ${left-data} to index ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"start\", \"right-data\":\"end\"}}', 'forloop', NULL, 'forloop', 1097, 10211, 20165, 30194, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (20175, 'AndroidNative', 'INDEX', 'Loop over data set in ${test-data-profile} from index start to index end', '{\"test-data\":{\"test-data-profile\":\"test data profile\"}}', 'forloop', NULL, 'forloop', 1098, 10212, 20166, 30195, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (20176, 'AndroidNative', 'PARAMETER_VALUE', 'Loop over data set in ${test-data-profile} when ${left-data} value ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"parameter\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1099, 10226, 20176, 30196, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (20177, 'AndroidNative', 'PARAMETER_VALUE', 'Loop over data set ${test-data-profile} when ${left-data} value ${operator}','{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"isEmpty/isNotEmpty\", \"left-data\":\"parameter\"}}', 'forloop', NULL, 'forloop', 1111, 10227, 20177, 30197, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"isEmpty\", \"isNotEmpty\"]', 'FOR_LOOP');


INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (30193, 'IOSNative', 'SET_NAME', 'Loop over data set in ${test-data-profile} where set name ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1096, 10210, 20164, 30193, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (30194, 'IOSNative', 'INDEX', 'Loop over data set in ${test-data-profile} from index ${left-data} to index ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"start\", \"right-data\":\"end\"}}', 'forloop', NULL, 'forloop', 1097, 10211, 20165, 30194, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (30195, 'IOSNative', 'INDEX', 'Loop over data set in ${test-data-profile} from index start to index end', '{\"test-data\":{\"test-data-profile\":\"test data profile\"}}', 'forloop', NULL, 'forloop', 1098, 10212, 20166, 30195, '2022-12-02 14:54:08', '2022-12-02 14:54:08', NULL, 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (30196, 'IOSNative', 'PARAMETER_VALUE', 'Loop over data set in ${test-data-profile} when ${left-data} value ${operator} ${right-data}', '{\"test-data\":{\"test-data-profile\":\"test data profile\",\"left-data\":\"parameter\",\"operator\":\"contains/startwith/Endswith/Equals/IN\", \"right-data\":\"filterParameter\"}}', 'forloop', NULL, 'forloop', 1099, 10226, 20176, 30196, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"contains\",\"startwith\",\"Endswith\",\"Equals\", \"IN\"]', 'FOR_LOOP');
INSERT INTO `natural_text_actions` (`id`, `workspace_type`, `loop_type`, `natural_text`, `data`, `display_name`, `snippet_class`, `action`, `import_to_web`, `import_to_mobile_web`, `import_to_android_native`, `import_to_ios_native`, `created_date`, `updated_date`, `allowed_values`, `condition_type`) VALUES (30197, 'IOSNative', 'PARAMETER_VALUE', 'Loop over data set ${test-data-profile} when ${left-data} value ${operator}','{\"test-data\":{\"test-data-profile\":\"test data profile\",\"operator\":\"isEmpty/isNotEmpty\", \"left-data\":\"parameter\"}}', 'forloop', NULL, 'forloop', 1111, 10227, 20177, 30197, '2022-12-02 14:54:08', '2022-12-02 14:54:08', '[\"isEmpty\", \"isNotEmpty\"]', 'FOR_LOOP');


INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (869,1107,'Loop over Data set in Login Data List where set name contains qateam','In this example, execution run on iteration of test data profile with operation of filtering parameter value','','{\"test data profile\":\"Login Data List\",\"contains/startwith/Endswith/Equals/IN\":\"contains\", \"right-data\":\"qateam\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (870,1108,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\",\"start\":\"start\", \"end\":\"end\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (871,1109,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (872,1110,'Loop over data set in User_list when role value Equals admin','In this example, execution run on iteration of test data profile with data set name operation of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (873,10223,'Loop over Data set in Login Data List where set name contains qateam','In this example, execution run on iteration of test data profile with operation of filtering parameter value','','{\"test data profile\":\"Login Data List\",\"contains/startwith/Endswith/Equals/IN\":\"contains\", \"right-data\":\"qateam\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (874,10224,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\",\"start\":\"start\", \"end\":\"end\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (875,10225,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (876,10226,'Loop over data set in User_list when role value Equals admin','In this example, execution run on iteration of test data profile with data set name operation of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (877,20173,'Loop over Data set in Login Data List where set name contains qateam','In this example, execution run on iteration of test data profile with operation of filtering parameter value','','{\"test data profile\":\"Login Data List\",\"contains/startwith/Endswith/Equals/IN\":\"contains\", \"right-data\":\"qateam\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (878,20174,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\",\"start\":\"start\", \"end\":\"end\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (879,20175,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (880,20176,'Loop over data set in User_list when role value Equals admin','In this example, execution run on iteration of test data profile with data set name operation of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (881,30193,'Loop over Data set in Login Data List where set name contains qateam','In this example, execution run on iteration of test data profile with operation of filtering parameter value','','{\"test data profile\":\"Login Data List\",\"contains/startwith/Endswith/Equals/IN\":\"contains\", \"right-data\":\"qateam\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (882,30194,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\",\"start\":\"start\", \"end\":\"end\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (883,30195,'Loop over data set in Add_User_Data_List from index start to index end','In this example, execution run on iteration of test data profile with start index and end index','','{\"test data profile\":\"Add_User_Data_List\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (884,30196,'Loop over data set in User_list when role value Equals admin','In this example, execution run on iteration of test data profile with data set name operation of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());

INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (885,1111,'Loop over data set in User_list when role value isEmpty','In this example, execution run on iteration of test data profile with data set name isEmpty/notEmpty of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (886,10227,'Loop over data set in User_list when role value isEmpty','In this example, execution run on iteration of test data profile with data set name isEmpty/notEmpty of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (887,20177,'Loop over data set in User_list when role value isEmpty','In this example, execution run on iteration of test data profile with data set name isEmpty/notEmpty of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());
INSERT INTO `natural_text_action_examples` (`id`, `natural_text_action_id`, `description`, `example`, `workspace`, `data`, `created_date`, `updated_date`) VALUES (888,30197,'Loop over data set in User_list when role value isEmpty','In this example, execution run on iteration of test data profile with data set name isEmpty/notEmpty of filtering parameter value','','{\"test data profile\":\"User_list\",\"parameter\":\"role\",\"contains/startwith/Endswith/Equals/IN\":\"Equals\", \"filterParameter\":\"admin\"}',now(),now());