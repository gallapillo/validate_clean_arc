package com.gallapillo.clean_form.domain.use_cases

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setUp() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `Password is letter only returns error`() {
        val res = validatePassword.execute("abcdefg")

        assertEquals(false, res.successful)
    }

    @Test
    fun `Password is correct`() {
        val res = validatePassword.execute("asd123asd")

        assertEquals(true, res.successful)
    }

    @Test
    fun `Password is incorrect by digits`() {
        val res = validatePassword.execute("12314313413423")
        assertEquals(false, res.successful)
    }

    @Test
    fun `Password is incorrect by lenght`() {
        val res = validatePassword.execute("w21a")
        assertEquals(false, res.successful)
    }
}