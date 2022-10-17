package komal.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import komal.example.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

}
