/*
 * FileName: EventQueueScript.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年4月11日 下午2:17:43          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EventQueueScript {
    private static String script;

    /**
     * @param script
     */
    public EventQueueScript(String script) {
        super();
        this.script = script;
    }

    /**
     * @return the script
     */
    public String getScript() {
        return script;
    }

    /**
     * @param script the script to set
     */
    public void setScript(String script) {
        this.script = script;
    }

    public void exec(RedisTemplate<String, String> redisTemplate, List<String> list, String id) {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(String.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.execute(redisScript, redisSerializer, redisSerializer, list, id);
    }

    public void exec(RedisTemplate<String, String> redisTemplate, List<String> list) {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(String.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.execute(redisScript, redisSerializer, redisSerializer, list);
    }
}
