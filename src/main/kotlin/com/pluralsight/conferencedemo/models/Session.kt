package com.pluralsight.conferencedemo.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity(name = "sessions")
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var session_id: Long = 0
    var session_name: String = ""
    var session_description: String = ""
    var session_length: Int = 0

    @ManyToMany
    @JoinTable(
        name = "session_speakers",
        joinColumns = [jakarta.persistence.JoinColumn(name = "session_id")],
        inverseJoinColumns = [jakarta.persistence.JoinColumn(name = "speaker_id")]
    )
    val speakers: List<Speaker?> = emptyList()

    fun Session() {}
}