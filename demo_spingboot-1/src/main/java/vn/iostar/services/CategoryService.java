package vn.iostar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostar.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	void deleteById(Long id);

	long count();

	Optional<Category> findById(Long id);

	Page<Category> findAll(Pageable pageable);

	<S extends Category> S save(S entity);

	List<Category> findAll(Sort sort);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	Optional<Category> findByName(String name);

}
