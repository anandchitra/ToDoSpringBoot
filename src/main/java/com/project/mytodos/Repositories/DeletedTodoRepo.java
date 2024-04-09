package com.project.mytodos.Repositories;

import com.project.mytodos.entity.Deletedtodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface DeletedTodoRepo extends JpaRepository<Deletedtodo, Integer> {
}
