package org.tcs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcs.entity.PostEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {
List<PostEntity> findByDeletedFalse();
    
    List<PostEntity> findByTitleContaining(String title);


}
