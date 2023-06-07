package com.pluralsight.conferencedemo.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class PersistanceConfiguration {
    @Bean
    fun dataSource(): DataSource {
        val builder = DataSourceBuilder.create()
        builder.url("jdbc:postgresql://172.18.0.2:5432/postgres")
        builder.username("postgres")
        builder.password("Welcome")
        println("The custom datasource bean has been initialized and set.")

        return builder.build()
    }
}