package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl : TodoRepository{

    @Autowired
    lateinit var todoDatabase: TodoDatabase

    override fun save(todo: Todo): Todo? {

        //1. index 있는 지?
      return todo.index?.let { index ->
            //있다면 update

             findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updateAt = LocalDateTime.now()
            }


        }?:kotlin.run {
            //없다면 insert

             todo.apply {
                this.index = ++todoDatabase.index
                this.createAt = LocalDateTime.now()
                this.updateAt = LocalDateTime.now()
            }.run {
                todoDatabase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try{
            todoList.forEach {
                save(it)
            }
            true
        }catch (e: Exception){
            false
        }
    }

    override fun delete(index: Int): Boolean {
       return findOne(index)?.let {
            todoDatabase.todoList.remove(it)
            true
        }?: kotlin.run{
            false
        }
    }

    override fun findOne(index: Int): Todo? {
       return todoDatabase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDatabase.todoList
    }
}