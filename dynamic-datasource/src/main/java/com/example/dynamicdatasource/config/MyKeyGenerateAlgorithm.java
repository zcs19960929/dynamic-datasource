package com.example.dynamicdatasource.config;

import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义分布式ID生成规则
 * Create DateTime: 2025/3/28 14:38
 *
 * @author zhangchangsheng
 **/
public class MyKeyGenerateAlgorithm implements KeyGenerateAlgorithm {


    private static AtomicLong counter = new AtomicLong(0);

    /**
     * Generate key.
     *
     * @return generated key
     */
    @Override
    public Comparable<?> generateKey() {
        return counter.incrementAndGet();
    }

    /**
     * Get properties.
     *
     * @return properties
     */
    @Override
    public Properties getProps() {
        return null;
    }

    /**
     * Initialize SPI.
     *
     * @param props properties to be initialized
     */
    @Override
    public void init(Properties props) {

    }

    /**
     * Get type.
     *
     * @return type
     */
    @Override
    public String getType() {
        return "MY_GENERATE_ID";
    }

}
