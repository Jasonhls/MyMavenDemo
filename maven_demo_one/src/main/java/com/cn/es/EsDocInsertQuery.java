package com.cn.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class EsDocInsertQuery {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9002, "http"))
        );

        //1. 查询索引中全部的数据
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));//全量查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //2.条件查询 ： termQuery
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //3. 分页查询 ：
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // from = ( 当前页码 - 1 ) * 每页显示数据条数
        builder.from(0);
        builder.size(10);
        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //4. 查询排序
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.DESC);
        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //5.过滤字段
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] excludes = {"age"};
        String[] includes = {};
        builder.fetchSource(includes, excludes);
        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //6.组合查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //与
//        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 30));
//        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));
//        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "男"));

        //或
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 40));
        builder.query(boolQueryBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //7.范围查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        //大于30，小于40
        rangeQuery.gte(30);
        rangeQuery.lte(40);

        builder.query(rangeQuery);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //8.模糊查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders
                .fuzzyQuery("name", "wangwu")
                .fuzziness(Fuzziness.ONE);//模糊查询偏差几个字符，Fuzziness.ONE表示最多差一个字符

        builder.query(fuzzyQueryBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //9.高亮查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "张三");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");//前置标签
        highlightBuilder.postTags("</font>");//后置标签
        highlightBuilder.field("name");//对name高亮

        builder.highlighter(highlightBuilder);
        builder.query(termQueryBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        //10.聚合查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();

        //查询最大年龄
        AggregationBuilder aggregationBuilder = AggregationBuilders
                .max("maxAge")
                .field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }*/

        // 11 分组查询
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();

        //根据年龄分组
        AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("ageGroup")
                .field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        esClient.close();
    }
}
