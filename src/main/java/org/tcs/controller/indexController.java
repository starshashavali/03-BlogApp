package org.tcs.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tcs.entity.PostEntity;
import org.tcs.repo.PostRepo;
import org.tcs.service.PostService;

@Controller
public class indexController {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PostService postService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/search")

	public String search(@RequestParam("title") String title, Model model) {

		List<PostEntity> searchResults = postRepo.findByTitleContaining(title);
		
		List<PostEntity> collect = searchResults.stream().filter(results -> results.getDeleted() == 0)
		.collect(Collectors.toList());

		model.addAttribute("blogs", collect);

		return "dashboardpage";

}
	@GetMapping("/dashboard")
	public String dashboradPage(Model model) {

		 List<PostEntity> posts = postService.getPosts();
		 
		 
		 List<PostEntity> collect = posts.stream().filter(results -> results.getDeleted() == 0)
					.collect(Collectors.toList());


		System.out.println(collect.size());
		
		model.addAttribute("blogs", collect);

		return "dashboardpage";

	}

	@GetMapping("/logout")
	public String logout() {

		session.invalidate();

		return "index";
	}

}
