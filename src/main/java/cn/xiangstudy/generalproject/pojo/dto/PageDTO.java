package cn.xiangstudy.generalproject.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

/**
 * @author zhangxiang
 * @date 2025-08-28 16:49
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页")
public class PageDTO {

    @Schema(description = "每页大小", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;

    @Schema(description = "当前页", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageNum;

    public void startPage(){
        pageSize = Optional.ofNullable(pageSize).orElse(10);
        pageNum = Optional.ofNullable(pageNum).orElse(1);
    }
}
