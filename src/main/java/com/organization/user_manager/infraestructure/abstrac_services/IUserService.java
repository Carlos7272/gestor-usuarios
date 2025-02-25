package com.organization.user_manager.infraestructure.abstrac_services;

import com.organization.user_manager.api.model.request.UserRequest;
import com.organization.user_manager.api.model.response.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long>, CatalogoServices <UserResponse>{

}
