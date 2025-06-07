package pers.xiaolin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author xiaolin03
 * @date 2025/6/1
 */
@Schema(description = "分页参数")
@Data
public class PageParam {
    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;
    @Schema(description = "页码")
    @Min(value = 1, message = "页码最小值为1")
    private Integer pageNo = PAGE_NO;

    @Schema(description = "每页记录数")
    @Max(value = 100, message = "每页记录数最大值为100")
    @Min(value = 1, message = "每页记录数最小值为1")
    private Integer pageSize = PAGE_SIZE;

    @Schema(description = "查询值")
    private String searchValue;
}
