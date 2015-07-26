package com.noodles.prompt.processor;

import com.noodles.prompt.Request;
import com.noodles.prompt.Response;

/**
 * 处理器
 * @author gaoxin.wei
 */
public interface Processor {

    public void process(Request request, Response response) throws Exception;
}
