package cn.devifish.cloud.common.handler

import cn.devifish.cloud.common.util.JsonUtils
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.io.Serializable
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * MyBatisJsonTypeHandler
 * MyBatis类型转换Handler (Object <-> Json)
 *
 * @author Devifish
 */
class MyBatisJsonTypeHandler<T : Serializable>(
        private val clazz: Class<T>
) : BaseTypeHandler<T>() {

    override fun getNullableResult(rs: ResultSet, columnName: String?): T? {
        return JsonUtils.toObject(rs.getString(columnName), clazz)
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): T? {
        return JsonUtils.toObject(rs.getString(columnIndex), clazz)
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): T? {
        return JsonUtils.toObject(cs.getString(columnIndex), clazz)
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: T, jdbcType: JdbcType) {
        ps.setString(i, JsonUtils.toJson(jdbcType))
    }

}