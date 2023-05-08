package org.tcs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcs.binding.CommentForm;
import org.tcs.entity.CommentsEntity;
import org.tcs.entity.PostEntity;
import org.tcs.entity.UserEntity;
import org.tcs.repo.CommentRepo;
import org.tcs.repo.PostRepo;
import org.tcs.repo.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private HttpSession session;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public String addComment(CommentForm form, Integer blogId) {
		// TODO: copy the form data to entity

		CommentsEntity entity = new CommentsEntity();

		BeanUtils.copyProperties(form, entity);

		// TODO: get post id and using that retrieve the post details , set to the
		// entity

		Optional<PostEntity> postDetails = postRepo.findById(blogId);

		PostEntity createPostDetails = postDetails.get();

		entity.setPosts(createPostDetails);

		// TODO: finally save the comments

		commentRepo.save(entity);

		return "Your Comment is added";
	}

	@Override
	public List<CommentsEntity> getComments() {
		Integer userId = (Integer) session.getAttribute("userId");

		Optional<UserEntity> findById = userRepo.findById(userId);
		UserEntity userDetails = findById.get();
		List<PostEntity> createPostDetails = userDetails.getPosts();

		List<CommentsEntity> listComments = userDetails.getPosts().stream().flatMap(post -> post.getComments().stream())
				.collect(Collectors.toList());

		return listComments;

	}

	@Override
	public void softDelete(Integer blogId) {
		Optional<PostEntity> postDetails = postRepo.findById(blogId);
		if (postDetails.isPresent()) {
			PostEntity createPostDetails = postDetails.get();
			createPostDetails.setDeleted(1);

			postRepo.save(createPostDetails);
		}

	}

	@Override
	public List<PostEntity> getActivePosts() {
		return postRepo.findByDeletedFalse();

	}

}
