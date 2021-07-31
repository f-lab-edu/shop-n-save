package com.flab.shopnsave.enums;

import com.flab.shopnsave.system.exception.UnnownEnumValueException;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Enum(Java) ↔ int(DB)와 같이 자바 타입과 JDBC 타입이 일치하지 않는 경우
 * MyBatis는 ResultSet에서 값을 가져올때마다 적절한 자바 타입의 값을 가져오기 위해 `TypeHandler`를 사용합니다.
 *
 * 표준 자바 타입의 경우 사용할 수 있는 디폴트 TypeHandlers가 있으나
 * 비표준인 Enum class 오브젝트를 사용하기 때문에 TypeHandler 인터페이스를 오버라이드하여 사용합니다.
 *
 * 이러한 Enum 클래스마다 TypeHandler 인터페이스를 구현하는 것은 파일의 수도 많아지고 관리가 힘들어지기 때문에
 * 해당 이슈가 있는 Enum 클래스에 대한 공통 인터페이스 CodeEnum과 제네릭으로 범용적인 사용가능한 CodeEnumTypeHandler를 사용합니다.
 */
@NoArgsConstructor
public class CodeEnumTypeHandler<E extends CodeEnum> implements TypeHandler<CodeEnum> {

    public Class<E> type;

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
        return Arrays.stream((CodeEnum[]) type.getEnumConstants())
                .filter(anEnum -> anEnum.getCode() == code).findAny()
                .orElseThrow(() -> new UnnownEnumValueException(type.getSimpleName(), code));
    }
}
