/*
 * FileName: OneLevelAsynContent.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.ThreadUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

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
 * 2019年3月28日 下午3:55:01          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
@Slf4j
public class OneLevelAsynContent {

    private AsyncListener asyncListener;

    ThreadPoolExecutor executor = null;

    public void submitFuture(final HttpServletRequest request, final Callable<Object> task) {
        final String uri = request.getRequestURI();
        final Map<String, String[]> params = request.getParameterMap();
        final AsyncContext asyncContext = request.getAsyncContext();

        asyncContext.setTimeout(1000);
        if (asyncListener != null) {
            asyncContext.addListener(asyncListener);
        }
        executor.submit(new CanceledCallable(asyncContext) {
            /* (non-Javadoc)
             * @see com.ultrapower.viedo.utils.ThreadUtils.CanceledCallable#run()
             */
            @Override
            public Object call() throws Exception {

                Object object = task.call();
                if (object instanceof CompletableFuture) {
                    CompletableFuture<Object> future = (CompletableFuture<Object>) object;
                    ((CompletableFuture) object).thenAccept(resultObject -> {
                        callback(asyncContext, object, uri, params);
                    }).exceptionally(e -> {
                        callback(asyncContext, object, uri, params);
                        return null;
                    });
                } else if (object instanceof String) {
                    callback(asyncContext, object, uri, params);
                }
                return null;

            }

        });
    }

    public void callback(AsyncContext asyncContext, Object result, String uri, Map<String, String[]> params) {
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
        try {
            if (result instanceof String) {
                write(response, (String) result);
            } else {
                write(response, JSON.toJSON(result).toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.info("async request reject ,uri:{},params:{}", uri, JSON.toJSON(params), e);
        } finally {
            asyncContext.complete();
        }

    }

    public void write(HttpServletResponse response, String result) throws IOException {
        PrintWriter writer;

        writer = response.getWriter();
        writer.write(result);

    }

    @PostConstruct
    public void afterPropertiesSet() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(1, 1, 10000, TimeUnit.SECONDS, queue);
        executor.allowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof CanceledCallable) {
                    CanceledCallable canceledCallable = (CanceledCallable) r;
                    AsyncContext context = canceledCallable.getAsyncContext();

                    if (context != null) {
                        String uri = (String) context.getRequest().getAttribute("uri");
                        Map<String, Object> params = (Map<String, Object>) context.getRequest().getAttribute("params");
                        log.info("async request reject ,uri:{},params:{}", uri, JSON.toJSON(params));
                    }
                    try {
                        HttpServletResponse response = (HttpServletResponse) context.getResponse();
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    } catch (Exception e) {
                        // TODO: handle exception
                    } finally {
                        context.complete();
                    }

                }
                if (asyncListener == null) {
                    asyncListener = new AsyncListener() {

                        @Override
                        public void onTimeout(AsyncEvent arg0) throws IOException {

                            AsyncContext context = arg0.getAsyncContext();

                            if (context != null) {
                                String uri = (String) context.getRequest().getAttribute("uri");
                                Map<String, Object> params = (Map<String, Object>) context.getRequest().getAttribute("params");
                                log.info("async request reject ,uri:{},params:{}", uri, JSON.toJSON(params));
                            }
                            try {
                                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            } catch (Exception e) {
                                // TODO: handle exception
                            } finally {
                                context.complete();
                            }

                        }

                        @Override
                        public void onStartAsync(AsyncEvent arg0) throws IOException {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onError(AsyncEvent arg0) throws IOException {
                            AsyncContext context = arg0.getAsyncContext();

                            if (context != null) {
                                String uri = (String) context.getRequest().getAttribute("uri");
                                Map<String, Object> params = (Map<String, Object>) context.getRequest().getAttribute("params");
                                log.info("async request onError ,uri:{},params:{}", uri, JSON.toJSON(params));
                            }
                            try {
                                HttpServletResponse response = (HttpServletResponse) context.getResponse();
                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            } catch (Exception e) {
                                // TODO: handle exception
                            } finally {
                                context.complete();
                            }

                        }

                        @Override
                        public void onComplete(AsyncEvent arg0) throws IOException {
                            // TODO Auto-generated method stub

                        }
                    };
                }
            }
        });

    }

}
