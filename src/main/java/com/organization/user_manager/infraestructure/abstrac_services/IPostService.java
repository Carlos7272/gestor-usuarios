package com.organization.user_manager.infraestructure.abstrac_services;

import com.organization.user_manager.api.model.request.PostRequest;
import com.organization.user_manager.api.model.response.PostResponse;

public interface IPostService extends CrudService<PostRequest, PostResponse, Long>, CatalogoServices <PostResponse>{
}
