package com.sourabhkarkal.blogreaderdemo.repo.storage.room

import com.sourabhkarkal.blogreaderdemo.model.MediaDTO
import com.sourabhkarkal.blogreaderdemo.model.UserDTO
import org.junit.Assert.*
import org.junit.Test


class RoomConverterTest {

    private val roomConverter = RoomConverter();

    @Test
    fun testFromMediaDTOString() {
        val mediaDTOString = "[{\"id\":\"1\",\"blogId\":\"1\",\"createdAt\":\"2020-04-16T22:43:18.606Z\",\"image\":\"https://s3.amazonaws.com/uifaces/faces/twitter/joe_black/128.jpg\",\"title\":\"maximized system\",\"url\":\"http://providenci.com\"}]"
        val mediaData = roomConverter.fromMediaDTOString(mediaDTOString)
        assertEquals("2020-04-16T22:43:18.606Z", mediaData?.get(0)?.createdAt);
    }

    @Test
    fun testFromMediaDTOArray() {
        var mediaDTO = MediaDTO("2020-04-16T22:43:18.606Z","https://s3.amazonaws.com/uifaces/faces/twitter/joe_black/128.jpg",
            "22", "maximized system")
        val listMedia = ArrayList<MediaDTO>();
        listMedia.add(mediaDTO)
        val mediaData = roomConverter.fromMediaDTOArrayList(listMedia)

        assertTrue(mediaData?.contains("maximized system")!!)

        assertFalse(mediaData?.contains("1234")!!)
    }

    @Test
    fun testFromUserDTOString() {
        val userDTOString = "[{\"id\":\"10\",\"blogId\":\"10\",\"createdAt\":\"2020-04-16T21:13:15.203Z\",\"name\":\"Ryann\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/davidhemphill/128.jpg\",\"lastname\":\"Rohan\",\"city\":\"Tiaraport\",\"designation\":\"Chief Interactions Producer\",\"about\":\"If we input the firewall, we can get to the AI sensor through the redundant PNG bus!\"}]"
        val userData = roomConverter.fromUserDTOString(userDTOString)
        assertEquals("Ryann", userData?.get(0)?.name);
    }

    @Test
    fun testFromUserDTOArray() {
        var userDTO = UserDTO("2020-04-16T22:43:18.606Z","Pune",
            "Sourabh", "Test")
        val listUser = ArrayList<UserDTO>();
        listUser.add(userDTO)
        val userData = roomConverter.fromUserDTOArrayList(listUser)

        assertTrue(userData?.contains("Sourabh")!!)

        assertFalse(userData?.contains("Ryann")!!)
    }

}