package assignment.cowaysystem.feature.order.entity.base

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseEntity : BaseTimeEntity() {
    @CreatedBy
    @Column(updatable = false)
    private val createBy: String? = null

    @LastModifiedBy
    private val lastModifiedBy: String? = null
}
