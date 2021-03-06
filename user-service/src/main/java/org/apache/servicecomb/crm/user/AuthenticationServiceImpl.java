/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.crm.user;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.crm.user.api.AuthenticationService;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "authentication")
@RequestMapping(path = "/")
public class AuthenticationServiceImpl implements AuthenticationService {

  private final TokenStore tokenStore;

  @Autowired
  public AuthenticationServiceImpl(TokenStore tokenStore) {
    this.tokenStore = tokenStore;
  }

  @Override
  @GetMapping(path = "validate")
  public String validate(String token) {
    String userName = tokenStore.validate(token);
    if (userName == null) {
      throw new InvocationException(BAD_REQUEST, "incorrect token");
    }
    return userName;
  }
}