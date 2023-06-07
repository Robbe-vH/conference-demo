package com.pluralsight.conferencedemo.repositories

import com.pluralsight.conferencedemo.models.Session
import org.springframework.data.jpa.repository.JpaRepository

interface ISessionRepo: JpaRepository<Session, Long>