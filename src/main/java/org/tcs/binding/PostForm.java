package org.tcs.binding;

import lombok.Data;

@Data
public class PostForm {
	private Integer postId;

	private String title;

	private String description;

	private String content;

	private Boolean delete;

}
