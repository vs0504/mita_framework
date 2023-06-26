package com.mita.repository;

import com.mita.model.DefaultDataGeneratorFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DefaultDataGeneratorFilesRepository extends JpaRepository<DefaultDataGeneratorFile, Long> {
}

