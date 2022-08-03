package com.cn.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

public class EsIndexDelete {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9002, "http"))
        );

        //删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse acknowledgedResponse = esClient.indices().delete(request, RequestOptions.DEFAULT);
        //响应状态
        System.out.println(acknowledgedResponse);

        esClient.close();
    }
}
