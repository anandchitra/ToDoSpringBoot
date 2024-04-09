package com.project.mytodos.Repositories;

import com.project.mytodos.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ToDoRepo extends JpaRepository<Todo,Integer> {
    List<Todo> findByDone(boolean done);
}
