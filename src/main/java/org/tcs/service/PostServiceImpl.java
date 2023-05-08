package org.tcs.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcs.binding.PostForm;
import org.tcs.entity.PostEntity;
import org.tcs.entity.UserEntity;
import org.tcs.repo.CommentRepo;
import org.tcs.repo.PostRepo;
import org.tcs.repo.UserRepo;
@Service
public class PostServiceImpl  implements PostService{

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private HttpSession session;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public String savePost(PostForm form) {

		Integer userId = (Integer) session.getAttribute("userId");

		Optional<UserEntity> findById2 = userRepo.findById(userId);

		UserEntity userDetails = findById2.get();

		if (form.getPostId() != null) {

			Optional<PostEntity> findById = postRepo.findById(form.getPostId());

			PostEntity updatedDetails = findById.get();

			updatedDetails.setTitle(form.getTitle());

			// updatedDetails.setTitle(form.getTitle());

			updatedDetails.setDescription(form.getDescription());

			postRepo.save(updatedDetails);

			return "records updated sucessfully";

		}

		PostEntity entity = new PostEntity();

		BeanUtils.copyProperties(form, entity);

		entity.setUsers(userDetails);

		postRepo.save(entity);

		return "post saved sucessfully";
	}
	

	@Override
	public List<PostEntity> getPosts() {

		Integer userId = (Integer) session.getAttribute("userId");

		// TODO: get the userdetails

		Optional<UserEntity> findById = userRepo.findById(userId);

		UserEntity userDetails = findById.get();

		// TODO: get the posts of logged in user

		List<PostEntity> totalPosts = userDetails.getPosts();

		return totalPosts;
	}

}
