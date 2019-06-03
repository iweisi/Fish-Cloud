package cn.devifish.cloud.admin.common.entity.enums

import cn.devifish.cloud.common.base.ConvertibleEnum
import cn.devifish.cloud.common.base.JsonSerializeEnum
import com.baomidou.mybatisplus.annotation.EnumValue

/**
 * MenuType 菜单类型
 *
 * @author Devifish
 */
enum class MenuType(
        @EnumValue
        private val value: Int
): ConvertibleEnum<Int>, JsonSerializeEnum {

    MENU(1),
    BUTTON(2);

    override fun getValue(): Int = this.value

    override fun getJsonValue(): String = this.value.toString()

}