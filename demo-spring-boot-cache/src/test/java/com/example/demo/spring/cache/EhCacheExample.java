package com.example.demo.spring.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Test;

/**
 * @author xiangzhurui
 * @version 2018/10/16 15:13
 */
public class EhCacheExample {

    @Test
    public void ehCache() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println(tmpDir);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(tmpDir))
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100))
                                .build()
                )
                .build(true);

        Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

        Cache<Long, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.heap(100)).build());

        myCache.put(1L, "da one!");

        String value = myCache.get(1L);

        System.out.println(value);
        System.out.println(value);

        cacheManager.close();

    }
}
