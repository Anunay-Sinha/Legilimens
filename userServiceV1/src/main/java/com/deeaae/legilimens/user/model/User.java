package com.deeaae.legilimens.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@JsonInclude(Include.NON_NULL)
public class User {

  @Id
  private String id;
  private String displayName;
  @Indexed(unique = true)
  private String username;
  @Indexed(unique = true)
  private String emailId;
  private String password;
  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
