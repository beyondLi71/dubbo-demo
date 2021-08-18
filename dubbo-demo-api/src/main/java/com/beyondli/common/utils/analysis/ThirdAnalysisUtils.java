package com.beyondli.common.utils.analysis;

/**
 * Created by beyondLi on 2021/7/12 12:41
 */

import com.beyondli.common.exception.PhantomException;
import com.beyondli.common.tools.apiresult.AbstractApiResult;
import com.beyondli.common.utils.uuid.UUIDUtils;

import java.io.Serializable;

/**
 * @Description: 三方解析相关工具
 * @ClassName: ThirdAnalysisUtils
 * @Author: beyondLi
 * @Date: 2021/7/12 12:41   
 */

public class ThirdAnalysisUtils implements Serializable {

    public static<T> T maxrockyAnalysis(AbstractApiResult<T> data) {
        if(!"0".equals(data.getCode())) {
            throw new PhantomException(UUIDUtils.getUUID(), data.getAppName(), data.getCode(), data.getMsg());
        }
        return data.getData();
    };
}
