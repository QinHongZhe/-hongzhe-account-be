package com.p.v2iserver.account.entitys.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.mapstruct.Mapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-08-26 11:22
 **/
@Data
public class BaseDTO {
    @ApiModelProperty("page默认最小值为1")
    @NotNull(message = "page拼写错误")
    @Min(value = 1)
    public Integer page;
    @ApiModelProperty("limit默认最小值为1")
    @NotNull(message = "limit拼写错误")
    @Min(value = 1)
    public Integer limit;
    @ApiModelProperty("0-添加,1-修改,2-删除（单条） 取值范围【-1,2】")
    @NotNull(message = "操作类型错误")
    @Min(value = -1)
    @Max(value = 2)
    public Integer type;
    @ApiModelProperty("创建时间")
    public Date createTime;
    @ApiModelProperty("更新时间")
    public Date updateTime;
}
