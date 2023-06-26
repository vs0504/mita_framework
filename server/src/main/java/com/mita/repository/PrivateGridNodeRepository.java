
package com.mita.repository;

import com.mita.model.PrivateGridNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface PrivateGridNodeRepository extends JpaRepository<PrivateGridNode, Long> {

  Page<PrivateGridNode> findAll(Specification specification, Pageable pageable);

}
