package com.flab.demo.enums;

import com.flab.demo.enums.CodeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeEnumTypeHandler<E extends Enum<E>> implements TypeHandler<CodeEnum> {

    public Class<E> type;

    public CodeEnumTypeHandler() {
    }

    public CodeEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, CodeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public CodeEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return getEnum(code);
    }

    @Override
    public CodeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return getEnum(code);
    }

    @Override
    public CodeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return getEnum(code);
    }

    private CodeEnum getEnum(int code) {
        CodeEnum[] enumConstants = (CodeEnum[])type.getEnumConstants();
        for(CodeEnum anEnum : enumConstants) {
            if(anEnum.getCode() == code) {
                return anEnum;
            }
        }
        return null;
    }
}
