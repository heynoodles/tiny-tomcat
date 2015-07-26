package com.noodles.prompt.processor;

import com.noodles.prompt.Request;
import com.noodles.prompt.Response;

/**
 * @author gaoxin.wei
 */
public class StaticResoureProcessor implements Processor {
    @Override
    public void process(Request request, Response response) throws Exception {
        response.renderStaticResource();
    }
}
