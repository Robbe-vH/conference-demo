package com.pluralsight.conferencedemo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.ManyToMany

@Entity(name = "speakers")
class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var speaker_id: Long = 0
    var first_name: String = ""
    var last_name: String = ""
    var title: String = ""
    var company: String = ""
    var speaker_bio: String = ""

    @Lob
    var speaker_photo: ByteArray = emptyArray<Byte>().toByteArray()

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore // This is the fix for the infinite loop together with the @JsonManagedReference in Session
    var sessions: MutableList<Session> = mutableListOf()
}