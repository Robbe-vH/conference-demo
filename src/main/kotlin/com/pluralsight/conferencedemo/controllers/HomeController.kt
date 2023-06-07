package com.pluralsight.conferencedemo.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(@Value("\${app.version}") private val appVersion: String = "", @Value("\${app.author}") private val author: String = "") {
    @GetMapping
    @RequestMapping("/")
    fun getStatus(): Map<String, String> = mapOf("app-version" to appVersion, "app-status" to "up", "app-author" to author)
}