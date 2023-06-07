package com.pluralsight.conferencedemo.controllers

import com.pluralsight.conferencedemo.models.Speaker
import com.pluralsight.conferencedemo.repositories.ISpeakerRepo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/speakers")
class SpeakersController(private val speakersRepository: ISpeakerRepo) {
    /**
     * Returns a list of all speakers.
     * Returns empty list if no speakers exist.
     */
    @GetMapping
    fun list(): List<Speaker> = speakersRepository.findAll()

    /**
     * Returns the speaker with the given ID.
     * Returns null if no speaker with the given ID exists.
     */
    @GetMapping
    @RequestMapping("/{id}")
    fun get(@PathVariable id: Long): Speaker = speakersRepository.findById(id).get()

    /**
     * Creates a new speaker.
     * Returns the created speaker.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody speaker: Speaker): Speaker = speakersRepository.saveAndFlush(speaker)

    /**
     * Deletes the speaker with the given ID.
     * Returns null if no speaker with the given ID exists.
     */
    @DeleteMapping
    @RequestMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): Unit = speakersRepository.deleteById(id)

    /**
     * Updates the speaker with the given ID. This is a PUT, so we need to pass in all the fields.
     * That is where BeanUtils is for. It will copy the existing speaker into the new speaker.
     * Returns the updated speaker or null.
     */
    @PutMapping
    @RequestMapping("/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody speaker: Speaker): Speaker {
        val existingSpeaker = speakersRepository.findById(id).get()
        return run {
            BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id")
            speakersRepository.saveAndFlush(existingSpeaker)
        }
    }
}