package com.lq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: liuqianqian
 * @CreateTime: 2022-10-09  09:07
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
@EnableKafka
public class KafkaConsumer {

//    @KafkaListener(topics = {"fleet_fms_tboxdata"})
//    public void onMessageHeartFleet(ConsumerRecord<?, ?> consumerRecord) {
//        log.info("dadsa");
//        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
//        if (optional.isPresent()) {
//            Object msg = optional.get();
//            if (msg instanceof String) {
//                String msgHeart= (String) msg;
//                log.info("车队开始消费心跳:{}",AesUtil.decrypt(msgHeart,FLEET_FMS_PASSWORD));
//            }
//        }
//    }

//    @KafkaListener(topics = {"zq_veh_analysisdata"})
    @KafkaListener(topics = {"zq_iov_tboxdata"},concurrency = "3")
    public void test(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            if (msg instanceof String) {
                String HtString = (String) msg;
                String decryHtString = AesUtil.decrypt(HtString, "7031850221971759169APVDUYKJSZIT");//调用aes解密方法


                String HtList[] = decryHtString.split("\\$");
                for (String ht : HtList) {
                    String htCols[] = ht.split("\\|");

                    if (ht.contains("50481956")) {
                        System.out.println(ht);
                    }

                    if ("50481956".equals(htCols[1]) || "80332244".equals(htCols[1]) || "50403090".equals(htCols[1])) {
                        System.out.println(ht);
                    }

                    if ("50152290".equals(htCols[1])) {
                        System.out.println(ht);
                    }
                    if (htCols.length < 80) {
                        continue;
                    }
                }
            }
        }

    }
//    @KafkaListener(topics = {"fleet_fms_faultdata"})
//    public void onMessageFaultFleet(ConsumerRecord<?, ?> consumerRecord) {
//        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
//        if (optional.isPresent()) {
//            Object msg = optional.get();
//            if (msg instanceof String) {
//                String msgHeart= (String) msg;
//
//                log.info("车队开始消费故障数据:{}",AesUtil.decrypt(msgHeart,FLEET_FMS_PASSWORD));
//            }
//        }
//    }


}
