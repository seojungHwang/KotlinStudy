package com.example.todo.config

import com.example.todo.database.TodoDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//어플리케이션이 구동될 때 참조해서 만든 빈을 먼저 만듦
@Configuration
class AppConfig {

    @Bean(initMethod = "init")
    fun todoDataBase(): TodoDatabase {
        return TodoDatabase()
    }
}