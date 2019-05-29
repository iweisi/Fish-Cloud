package cn.devifish.cloud.common.handler

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * MyBatisMetaObjectHandler
 * Mybatis 插入及修改默认值注入
 *
 * @author Devifish
 */
@Component
@ConditionalOnClass(MetaObjectHandler::class)
class MyBatisMetaObjectHandler : MetaObjectHandler {

    override fun insertFill(metaObject: MetaObject?) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject)
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject)
    }

    override fun updateFill(metaObject: MetaObject?) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }


}