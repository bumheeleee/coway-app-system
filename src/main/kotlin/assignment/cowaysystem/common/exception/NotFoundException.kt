package assignment.cowaysystem.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class NotFoundException(reason: String?) : ResponseStatusException(HttpStatus.NOT_FOUND, reason)