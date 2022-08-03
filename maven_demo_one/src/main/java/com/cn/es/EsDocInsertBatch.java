package com.cn.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsDocInsertBatch {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9002, "http"))
        );

        //批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "张三", "age", 10, "sex", "男"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "李四", "age", 20, "sex", "女"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "王五", "age", 30, "sex", "男"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        System.out.println(response.getItems());

        esClient.close();
    }
}
