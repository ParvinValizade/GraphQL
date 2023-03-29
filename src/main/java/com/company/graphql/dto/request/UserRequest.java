package com.company.graphql.dto.request;

import com.company.graphql.model.Role;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String mail;
    private Role role;
}
