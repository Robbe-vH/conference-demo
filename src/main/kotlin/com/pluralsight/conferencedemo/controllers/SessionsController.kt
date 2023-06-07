package com.pluralsight.conferencedemo.controllers

import com.pluralsight.conferencedemo.models.Session
import org.springframework.beans.factory.annotation.Autowired
import com.pluralsight.conferencedemo.repositories.ISessionRepo
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/sessions")
class SessionsController(private val sessionsRepository: ISessionRepo) {
    /**
     * Returns a list of all sessions.
     * Returns empty list if no sessions exist.
     */
    @GetMapping
    fun list(): List<Session> = sessionsRepository.findAll()

    /**
     * Returns the session with the given ID.
     * Returns null if no session with the given ID exists.
     */
    @GetMapping
    @RequestMapping("/{id}")
    fun get(@PathVariable id: Long): Session = sessionsRepository.findById(id).get()

    /**
     * Creates a new session.
     * Returns the created session.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody session: Session): Session = sessionsRepository.saveAndFlush(session)

    /**
     * Deletes the session with the given ID.
     * Returns null if no session with the given ID exists.
     */
    @DeleteMapping
    @RequestMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Long): Unit = sessionsRepository.deleteById(id)

    /**
     * Updates the session with the given ID. This is a PUT, so we need to pass in all the fields.
     * That is where BeanUtils is for. It will copy the existing session into the new session.
     * Returns the updated session or null.
     */
    @PutMapping
    @RequestMapping("/update/{id}")
    fun update(@PathVariable id: Long,  @RequestBody session: Session): Session {
        val existingSession = sessionsRepository.findById(id).get()
        return run {
            BeanUtils.copyProperties(session, existingSession, "session_id")
            sessionsRepository.saveAndFlush(existingSession)
        }
    }
}