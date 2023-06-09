package com.testsigma.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "workspaces_version_mapping")
@Data
public class WorkspaceVersionMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "workspace_vesrion_id")
    private Long workspaceVersionId;

    @Column(name = "status")
    private String status;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;
}


