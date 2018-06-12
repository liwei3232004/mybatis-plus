/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.core.conditions.interfaces;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 查询条件封装
 * 比较值
 * </p>
 *
 * @author hubin miemie HCL
 * @since 2017-05-26
 */
public interface Func<This, R> extends Serializable {

    /**
     * 字段 IS NULL
     */
    default This isNull(R column) {
        return isNull(true, column);
    }

    /**
     * 字段 IS NULL
     */
    This isNull(boolean condition, R column);

    /**
     * 字段 IS NOT NULL
     */
    default This isNotNull(R column) {
        return isNotNull(true, column);
    }

    /**
     * 字段 IS NOT NULL
     */
    This isNotNull(boolean condition, R column);

    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     */
    default This in(R column, Collection<?> value) {
        return in(true, column, value);
    }

    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     */
    This in(boolean condition, R column, Collection<?> value);

    /**
     * 字段 IN (v0, v1, ...)
     */
    default This in(R column, Object... values) {
        return in(true, column, values);
    }

    /**
     * 字段 IN (v0, v1, ...)
     */
    default This in(boolean condition, R column, Object... values) {
        return in(condition, column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{}))
            .collect(Collectors.toList()));
    }

    /**
     * 字段 NOT IN (value.get(0), value.get(1), ...)
     */
    default This notIn(R column, Collection<?> values) {
        return notIn(true, column, values);
    }

    /**
     * 字段 NOT IN (value.get(0), value.get(1), ...)
     */
    This notIn(boolean condition, R column, Collection<?> value);

    /**
     * 字段 NOT IN (v0, v1, ...)
     */
    default This notIn(R column, Object... value) {
        return notIn(true, column, value);
    }

    /**
     * 字段 NOT IN (v0, v1, ...)
     */
    default This notIn(boolean condition, R column, Object... values) {
        return notIn(condition, column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{}))
            .collect(Collectors.toList()));
    }

    /**
     * 分组：GROUP BY 字段, ...
     */
    default This groupBy(R column) {
        return groupBy(true, column);
    }

    /**
     * 分组：GROUP BY 字段, ...
     */
    This groupBy(boolean condition, R column);

    /**
     * 排序：ORDER BY 字段, ...
     */
    default This orderBy(R column) {
        return orderBy(true, column);
    }

    /**
     * 排序：ORDER BY 字段, ...
     */
    default This orderBy(boolean condition, R column) {
        return orderBy(condition, column, true);
    }

    /**
     * 排序：ORDER BY 字段, ...
     */
    default This orderBy(R column, boolean isAsc) {
        return orderBy(true, column, isAsc);
    }

    /**
     * 排序：ORDER BY 字段, ...
     */
    This orderBy(boolean condition, R column, boolean isAsc);

    /**
     * HAVING ( sql 语句 )
     * 例: having("sum(age) > {0}", 1)
     */
    default This having(String sqlHaving, Object... params) {
        return having(true, sqlHaving, params);
    }

    /**
     * HAVING ( sql 语句 )
     */
    This having(boolean condition, String sqlHaving, Object... params);
}