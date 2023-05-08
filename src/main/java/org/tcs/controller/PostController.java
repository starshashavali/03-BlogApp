package org.tcs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tcs.binding.PostForm;
import org.tcs.entity.PostEntity;
import org.tcs.repo.PostRepo;
import org.tcs.service.PostService;
import org.tcs.service.UserService;
@Controller
public class PostController {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserService service;
	@Autowired
	private PostService postService;

	

	@Autowired
	private HttpSession postSession;

	@GetMapping("/post")
	public String createPost(Model model) {

		model.addAttribute("newpost", new PostForm());

		return "postpage";

	}

	@PostMapping("/post")
	public String savePost(@ModelAttribute("newpost") PostForm form, Model model) {

		String status = postService.savePost(form);

		model.addAttribute("newpost", new PostEntity());

		model.addAttribute("msg", status);

		return "postpage";

	}

	@GetMapping("/edit")
	private String EditPost(@RequestParam("postId") Integer postId, Model model) {

		System.out.println(postId);

		Optional<PostEntity> findById = postRepo.findById(postId);

		if (findById.isPresent()) {

			PostEntity createPostDetails = findById.get();

			model.addAttribute("newpost", createPostDetails);
			model.addAttribute("hidden", postId);

		}

		return "postpage";
	}

	// search functionality

	@GetMapping("/searchblogs")

	public String search(@RequestParam("title") String title, Model model) {

		List<PostEntity> searchResults = postRepo.findByTitleContaining(title);

		model.addAttribute("bloglist", searchResults);

		return "allpost";

	}

}


