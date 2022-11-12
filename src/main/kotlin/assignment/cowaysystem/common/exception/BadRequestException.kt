package assignment.cowaysystem.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class BadRequestException(reason: String?) : ResponseStatusException(HttpStatus.BAD_REQUEST, reason)