package com.xiaojukeji.know.streaming.km.task.kafka.metrics;

import com.didiglobal.logi.job.annotation.Task;
import com.didiglobal.logi.job.common.TaskResult;
import com.didiglobal.logi.job.core.consensual.ConsensualEnum;
import com.didiglobal.logi.log.ILog;
import com.didiglobal.logi.log.LogFactory;
import com.xiaojukeji.know.streaming.km.collector.metric.kafka.BrokerMetricCollector;
import com.xiaojukeji.know.streaming.km.common.bean.entity.cluster.ClusterPhy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author didi
 */
@Task(name = "BrokerMetricCollectorTask",
        description = "Broker指标采集任务",
        cron = "0 0/1 * * * ? *",
        autoRegister = true,
        consensual = ConsensualEnum.BROADCAST,
        timeout = 2 * 60)
public class BrokerMetricCollectorTask extends AbstractAsyncMetricsDispatchTask {
    private static final ILog log = LogFactory.getLog(BrokerMetricCollectorTask.class);

    @Autowired
    private BrokerMetricCollector brokerMetricCollector;

    @Override
    public TaskResult processClusterTask(ClusterPhy clusterPhy, long triggerTimeUnitMs) throws Exception {
        brokerMetricCollector.collectMetrics(clusterPhy);

        return TaskResult.SUCCESS;
    }
}
