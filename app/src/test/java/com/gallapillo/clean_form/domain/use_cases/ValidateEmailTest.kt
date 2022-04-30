package com.gallapillo.clean_form.domain.use_cases

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail

    @Before
    fun setUp() {
        validateEmail = ValidateEmail()
    }

    @Test
    fun `Email not correct is letter only returns error`() {
        val res = validateEmail.execute("abcdefg")

        assertEquals(false, res.successful)
    }

    @Test
    fun `Email is blank`() {
        val res = validateEmail.execute("")

        assertEquals(true, res.successful)
    }

    @Test
    fun `Password is correct`() {
        val res = validateEmail.execute("gaosd@test.com")
        assertEquals(true, res.successful)
    }
}