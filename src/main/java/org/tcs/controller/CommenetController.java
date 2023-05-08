package org.tcs.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tcs.binding.CommentForm;
import org.tcs.entity.CommentsEntity;
import org.tcs.entity.PostEntity;
import org.tcs.repo.CommentRepo;
import org.tcs.repo.PostRepo;
import org.tcs.service.CommentService;
import org.tcs.service.PostService;
import org.tcs.service.UserService;
@Controller
public class CommenetController {
	@Autowired
	private UserService userservice;
	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private HttpSession session;
	@GetMapping("/allposts")
	public String allPosts(Model model) {

		List<PostEntity> findAll = postRepo.findAll();

		model.addAttribute("bloglist", findAll);

		return "allpost";
	}

	@GetMapping("/blog")

	public String showBlog(@RequestParam Integer postId, Model model) {

		session.setAttribute("blogId", postId);

		Optional<PostEntity> findBlog = postRepo.findById(postId);

		if (findBlog.isPresent()) {

			model.addAttribute("display", findBlog.get());

			model.addAttribute("comments", new CommentForm());

			model.addAttribute("reviews", findBlog.get().getComments());

			return "blogpage";
		}

		return "index";

	}




	@PostMapping("/comment")
	public String giveComment(@ModelAttribute("comments") CommentForm form, Model model) {

		Integer blogId = (Integer) session.getAttribute("blogId");

		String commentAdded = commentService.addComment(form, blogId);

		model.addAttribute("msg", commentAdded);


		// same code is avaliable in blog controller
		Optional<PostEntity> findBlog = postRepo.findById(blogId);

		model.addAttribute("display", findBlog.get());

		model.addAttribute("reviews", findBlog.get().getComments());

		model.addAttribute("comments", new CommentForm());

		return "blogpage";

	}

	@GetMapping("/showcomments")
	public String getComments(Model model) {

		List<CommentsEntity> retrieveComments = commentService.getComments();

		model.addAttribute("commentsList", retrieveComments);

		return "commentsPage";

	}

	@GetMapping("/deletecomment")
	public String deleteComment(@RequestParam("commentId") Integer commentId) {

		commentRepo.deleteById(commentId);

		// redirectAttributes.addFlashAttribute("message", "Comment deleted
		// successfully");
		return "redirect:/showcomments";

	}

	@GetMapping("/delete")
	public String softDeletePost(@RequestParam("postId") Integer blogId) {

		commentService.softDelete(blogId);
		return "redirect:/dashboard";
	}





	}




