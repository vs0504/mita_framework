package com.mita.repository;


import com.mita.model.StorageConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface StorageConfigRepository extends JpaRepository<StorageConfig, Integer> {
}
