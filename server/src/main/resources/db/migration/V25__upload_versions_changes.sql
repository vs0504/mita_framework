ALTER TABLE upload_versions ADD CONSTRAINT UNIQUE KEY `index_uploads_on_upload_id_and_name` (`upload_id`, `name`);