package assignment.cowaysystem.common.util

import org.springframework.validation.BindException
import org.springframework.validation.BindingResult

fun BindingResult.throwIfHasErrors() {
    if(this.hasErrors()) throw BindException(this)
}