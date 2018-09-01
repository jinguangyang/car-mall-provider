package com.car.api;

import cn.vcfilm.sts.framework.parameter.SingleParam;
import com.car.domain.IndexInfo;

/**
 * @Description [首页接口]
 * @Author <a href="mailto: wuyingya@hipiao.com">吴迎亚</a>
 * @Date 2018/8/31 11:14
 **/
public interface IndexService {

    /**
     * @Description [TODO]
     * @Date 2018/8/31 11:15
     * @Author:吴迎亚
     * @Param
     * @Return
     */
    SingleParam<IndexInfo> getDemo(String param);
}
