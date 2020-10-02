package com.deeaae.legilimens.user.service;

import java.util.Map;

public interface UserLogin {

  Map<String, String> login(String username, String password);
}
