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

    @Schema(description = "每页条数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer limit;

    @Schema(description = "页码", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;

    public void startPage(){
        limit = Optional.ofNullable(limit).orElse(10);
        page = Optional.ofNullable(page).orElse(1);
    }
}
