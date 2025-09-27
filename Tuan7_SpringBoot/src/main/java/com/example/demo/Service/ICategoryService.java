package com.example.demo.Service;

import com.example.demo.Entities.Category;
import com.example.demo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    <S extends Category> S save(S entity);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Integer> integers);

    Optional<Category> findById(Integer integer);

    <S extends Category> Optional<S> findOne(Example<S> example);

    <S extends Category> long count(Example<S> example);

    void deleteById(Integer integer);

    void delete(Category entity);

    void deleteAll();

    List<Category> findByNameContaining(String name);

    Page<Category> findByNameContaining(String name, Pageable pageable);
}
