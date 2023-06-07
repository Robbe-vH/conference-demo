package com.pluralsight.conferencedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConferenceDemoApplication

fun main(args: Array<String>) {
	runApplication<ConferenceDemoApplication>(*args)
}
