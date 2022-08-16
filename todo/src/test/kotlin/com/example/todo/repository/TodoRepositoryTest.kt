package com.example.todo.repository

import com.example.todo.config.AppConfig
import com.example.todo.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositortImpl: TodoRepositoryImpl

    //전체 실행하면 전체 초기화 해줌
    @BeforeEach
    fun before(){
        todoRepositortImpl.todoDatabase.init()  //db 초기화
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositortImpl.save(todo)

        Assertions.assertEquals(1, result?.index)  //기대값
        Assertions.assertNotNull(result?.createAt)
        Assertions.assertNotNull(result?.updateAt)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
    }

    @Test
    fun saveAllTest(){
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositortImpl.saveAll(todoList)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest(){
        todoRepositortImpl.todoDatabase.init()  //db 초기화

        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )
        todoRepositortImpl.saveAll(todoList)

        val result = todoRepositortImpl.findOne(2)
        println(result)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun update(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
       val insertTodo = todoRepositortImpl.save(todo)

        val newTodo = Todo().apply {
        this.index = insertTodo?.index
        this.title = "업데이트 일정"
        this.description = "업데이트"
        this.schedule = LocalDateTime.now()
        }
        val result = todoRepositortImpl.save(newTodo)
        println(result)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(insertTodo?.index, result?.index)
        Assertions.assertEquals("업데이트 일정", result?.title)
        Assertions.assertEquals("업데이트", result?.description)
    }
}