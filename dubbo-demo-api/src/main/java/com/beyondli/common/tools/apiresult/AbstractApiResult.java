package com.beyondli.common.tools.apiresult;

import com.beyondli.common.utils.springcontext.SpringContextUtil;
import com.beyondli.common.utils.uuid.UUIDUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author beyondLi
 * @desc  统一返回体.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "通用响应体", description = "通用返回使用响应体")
public class AbstractApiResult<T> implements Serializable {

    @ApiModelProperty(value = "id标识",example = "xxxxxxxxx")
    private String id;

    @ApiModelProperty(value = "服务名称",example = "maxrocky-service")
    private String appName;

    @ApiModelProperty(value = "code编码",example = "0")
    private String code;

    @ApiModelProperty(value = "返回接口状态信息",example = "ok")
    private String msg;

    @ApiModelProperty(value = "对应类")
    private T data;

    /**
     * 成功的返回
     * @param data 数据
     * @return 正常返回体
     */
    public static <T> AbstractApiResult success(T data) {
        return new AbstractApiResult(data);
    }

    AbstractApiResult(T data) {
        this.id = UUIDUtils.getUUID();
        this.code = "0";
        this.data = data;
        this.appName = SpringContextUtil.getBean("maxrockyApplicationName");
        this.msg = "success";
    }

}
