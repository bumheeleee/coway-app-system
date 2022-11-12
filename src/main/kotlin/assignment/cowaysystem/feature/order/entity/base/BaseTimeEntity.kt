package assignment.cowaysystem.feature.order.entity.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
}