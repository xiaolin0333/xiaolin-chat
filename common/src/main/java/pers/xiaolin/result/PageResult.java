package pers.xiaolin.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 */
@Schema(description = "分页结果")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    @Schema(description = "当前页数据集合")
    private List<T> records;

    @Schema(description = "总记录数")
    private Long total;

}
