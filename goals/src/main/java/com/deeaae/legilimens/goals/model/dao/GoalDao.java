package com.deeaae.legilimens.goals.model.dao;

import com.deeaae.legilimens.goals.model.GoalStatus;
import com.deeaae.legilimens.goals.model.GoalType;
import com.deeaae.legilimens.goals.model.RAGStatus;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoalDao {

  @Id
  String id;
  @Indexed
  String userId;
  String subject;
  String desc;
  @Indexed
  GoalType goalType;
  Set<String> taskIds;
  Set<String> alertIds;
  GoalStatus status;
  RAGStatus ragStatus;

  @LastModifiedDate
  LocalDateTime lastModifiedDate;

  @CreatedDate
  LocalDateTime createdDate;
  LocalDateTime startDate;
  LocalDateTime closeDate;


}
