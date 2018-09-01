package com.car.mall.service;

import cn.vcfilm.sts.framework.code.ReturnCode;
import cn.vcfilm.sts.framework.parameter.SingleParam;
import com.alibaba.dubbo.config.annotation.Service;
import com.car.api.IndexService;
import com.car.domain.IndexInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description [首页接口实现类]
 * @Author <a href="mailto: wuyingya@hipiao.com">吴迎亚</a>
 * @Date 2018/8/31 11:17
 **/
@Component
@Service(interfaceClass = IndexService.class)
public class IndexServiceImpl implements IndexService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description [TODO]
     * @Date 2018/8/31 11:15
     * @Author:吴迎亚
     * @Param
     * @Return
     */
    @Override
    public SingleParam<IndexInfo> getDemo(String param) {
        SingleParam<IndexInfo> entity = new SingleParam<>();
        IndexInfo indexInfo = new IndexInfo();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("首页测试方法: 参数:");
            stringBuilder.append(param);
            indexInfo.setInfo(stringBuilder.toString());
            entity.setStatus(ReturnCode.PROCESS_SUCCESS);
            entity.setData(indexInfo);
            entity.setMessage("操作成功");
        } catch (Exception e) {
            entity.setStatus(ReturnCode.PROCESS_ERROR);
            entity.setMessage("操作异常");
            entity.setErrorMessage(e.getMessage());
            logger.error("操作异常", e);
        }
        return entity;
    }
}
