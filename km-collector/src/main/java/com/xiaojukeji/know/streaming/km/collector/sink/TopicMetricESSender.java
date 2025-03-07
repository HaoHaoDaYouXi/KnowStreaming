package com.xiaojukeji.know.streaming.km.collector.sink;

import com.didiglobal.logi.log.ILog;
import com.didiglobal.logi.log.LogFactory;
import com.xiaojukeji.know.streaming.km.common.bean.event.metric.*;
import com.xiaojukeji.know.streaming.km.common.bean.po.metrice.*;
import com.xiaojukeji.know.streaming.km.common.utils.ConvertUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


import static com.xiaojukeji.know.streaming.km.common.constant.ESIndexConstant.TOPIC_INDEX;

@Component
public class TopicMetricESSender extends AbstractMetricESSender implements ApplicationListener<TopicMetricEvent> {
    protected static final ILog  LOGGER = LogFactory.getLog("METRIC_LOGGER");

    @PostConstruct
    public void init(){
        LOGGER.info("class=TopicMetricESSender||method=init||msg=init finished");
    }

    @Override
    public void onApplicationEvent(TopicMetricEvent event) {
        send2es(TOPIC_INDEX, ConvertUtil.list2List(event.getTopicMetrics(), TopicMetricPO.class));
    }
}
