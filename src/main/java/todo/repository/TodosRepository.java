package todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import todo.model.Todo;

public interface TodosRepository extends JpaRepository<Todo, Long> {

}
