//package com.lq.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.config.SslConfigs;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Properties;
//
///**
// * @Author: liuqianqian
// * @CreateTime: 2023-03-02  14:28
// * @Description: TODO
// * @Version: 1.0
// */
//@Component
//public class InitService {
//
//    @PostConstruct
//    public void init() {
//
////        String keystoreFile = confPath + File.separator + "app_dlake_client.keystore.jks";
//        String keystoreFile = "D:\\workspace\\demo\\mq\\config\\"+ "app_dlake_client.keystore.jks";
//        String truststoreFile = "D:\\workspace\\demo\\mq\\config\\" + "client.truststore.jks";
////        String truststoreFile = confPath + File.separator + "client.truststore.jks";
//
//        Properties appProperties = new Properties();
//
////        System.out.println(brokerlist);
//
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "prod-txbase-dn01.tongxin.cn:9797,prod-txbase-dn02.tongxin.cn:9798,prod-txbase-dn03.tongxin.cn:9799");
//        props.put("security.protocol", "SSL");
//        props.put("ssl.truststore.location", truststoreFile); // .jks format
//
//        props.put("ssl.keystore.location", keystoreFile); // .jks format
//        props.put("ssl.keystore.password", "dlake2022*?wit");
//        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "dlake2022*?wit");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fleet_fms");
//
//    }
//}
