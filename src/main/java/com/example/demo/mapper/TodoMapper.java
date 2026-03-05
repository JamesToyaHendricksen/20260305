package com.example.demo.mapper;

import java.util.List;

import com.example.demo.model.Todo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TodoMapper {

    @Select("SELECT id, title, completed FROM todos ORDER BY id DESC")
    List<Todo> findAll();

    @Insert("INSERT INTO todos (title, completed) VALUES (#{title}, FALSE)")
    int insert(@Param("title") String title);
}
