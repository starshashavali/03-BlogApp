package org.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcs.entity.CommentsEntity;

public interface CommentRepo extends JpaRepository<CommentsEntity, Integer> {

}
