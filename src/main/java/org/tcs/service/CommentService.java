package org.tcs.service;

import java.util.List;

import org.tcs.binding.CommentForm;
import org.tcs.entity.CommentsEntity;
import org.tcs.entity.PostEntity;

public interface CommentService {

	public String addComment (CommentForm form, Integer blogId);
	
	public List<CommentsEntity> getComments();
	
    public void softDelete(Integer blogId) ;
    
    public List<PostEntity> getActivePosts() ;


}
