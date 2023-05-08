package org.tcs.service;

import java.util.List;

import org.tcs.binding.PostForm;
import org.tcs.entity.PostEntity;

public interface PostService {

		public String savePost (PostForm form);
		
		public  List<PostEntity>  getPosts();

}
