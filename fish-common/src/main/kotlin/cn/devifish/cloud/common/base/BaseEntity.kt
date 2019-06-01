package cn.devifish.cloud.common.base

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * BaseEntity 实体类基类
 *
 * @author Devifish
 */
abstract class BaseEntity : Serializable {

    /*主键ID*/
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int? = null

    /*创建时间*/
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /*更新时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    /*删除标记*/
    @TableLogic
    var deleteFlag: Boolean? = null

    constructor()
    constructor(id: Int) {
        this.id = id;
    }

}