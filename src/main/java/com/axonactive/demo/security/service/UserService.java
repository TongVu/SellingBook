package com.axonactive.demo.security.service;

import com.axonactive.demo.security.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

}
