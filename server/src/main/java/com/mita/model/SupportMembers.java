

package com.mita.model;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;

@Log4j2
@Data
@Entity
@Table(name = "support_members")
public class SupportMembers implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "webhook_url")
  private String webhookUrl;

  @Column(name = "channel")
  private String channel;

  @Column(name = "is_active")
  private Boolean isActive;

}
