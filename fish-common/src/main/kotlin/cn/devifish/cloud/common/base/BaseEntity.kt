package cn.devifish.cloud.common.base

import java.io.Serializable
import java.time.LocalDateTime

/**
 * BaseEntity 实体类基类
 *
 * @author Devifish
 */
abstract class BaseEntity : Serializable {

    /*主键ID*/
    var id: Int? = null

    /*创建时间*/
    var createTime: LocalDateTime? = null

    /*更新时间*/
    var updateTime: LocalDateTime? = null

    /*删除标记*/
    var deleteFlag: Boolean? = null

    constructor()
    constructor(id: Int) {
        this.id = id;
    }

}