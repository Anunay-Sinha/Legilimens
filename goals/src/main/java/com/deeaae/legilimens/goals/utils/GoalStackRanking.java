package com.deeaae.legilimens.goals.utils;

import com.deeaae.legilimens.goals.model.GoalType;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class GoalStackRanking {

  static private Map<GoalType, Integer> stackRanking;

  static {
    stackRanking = new HashMap<>();
    stackRanking.put(GoalType.LIFE_CHANGING_GOALS, 1);
    stackRanking.put(GoalType.HEALTH_GOALS, 1);

    stackRanking.put(GoalType.PROPDUCTIVITY_GOALS, 2);
    stackRanking.put(GoalType.GOALS_WITH_EFFORT, 2);
    stackRanking.put(GoalType.LONG_GOALS, 2);
    stackRanking.put(GoalType.DREAM_GOALS, 2);

    stackRanking.put(GoalType.HABBIT_BUILDERS, 3);
    stackRanking.put(GoalType.SMALL_GOALS, 3);
  }

  public static Integer getRanking(GoalType goalType) {
    return stackRanking.get(goalType);
  }

}
