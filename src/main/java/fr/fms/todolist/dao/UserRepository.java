package fr.fms.todolist.dao;

import fr.fms.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
