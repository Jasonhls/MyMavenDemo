package com.cn.redis.study;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2021-06-01 16:07
 **/
public class BlogDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    public long getBlogId() {
        return jedis.incr("blog_id_counter");
    }

    /**
     * 发表一篇博客
     * @param id
     * @param title
     * @param content
     * @param author
     * @param time
     */
    public void publishBlog(long id, String title, String content, String author, String time) {
        jedis.msetnx("article:" + id + ":title", title,
                "article:" + id + ":content", content,
                "article:" + id + ":author", author,
                "article:" + id + ":time", time);
        Long length = jedis.strlen("article:" + id + ":content");
        jedis.setnx("article:" + id + ":content_length", String.valueOf(length));
    }

    /**
     * 查看博客
     * @param id
     * @return
     */
    public List<String> getBlog(long id) {
        List<String> blog = jedis.mget("article:" + id + ":title",
                "article:" + id + ":content",
                "article:" + id + ":author",
                "article:" + id + ":time",
                "article:" + id + ":content_length",
                "article:" + id + ":like_count");

        viewBlog(id);
        return blog;
    }

    /**
     * 更新一篇博客
     * @param id
     * @param title
     * @param content
     */
    public void updateBlog(long id, String title, String content) {
        jedis.mset("article:" + id + ":title", title,
                "article:" + id + ":content", content);
        Long length = jedis.strlen("article:" + id + ":content");
        jedis.setnx("article:" + id + ":content_length", String.valueOf(length));
    }

    /**
     * 预览博客，搜索博客结果页面里，对每个搜索结果都是看博客的预览内容
     * @param id
     * @return
     */
    public String previewBlog(long id) {
        return jedis.getrange("article:" + id + ":content", 0, 10);
    }

    /**
     * 对博客进行点赞
     * @param id
     */
    public void likeBlog(long id) {
        jedis.incr("article:" +id + ":like_count");
    }

    /**
     * 增加博客浏览次数
     * @param id
     */
    public void viewBlog(long id) {
        jedis.incr("article:" + id + ":view_count");
    }

    public static void main(String[] args) {
        //发表一篇博客
        BlogDemo demo = new BlogDemo();
        long id = demo.getBlogId();
        String title = "我喜欢学习redis";
        String content = "学习redis是一件特别快乐的事情";
        String author = "hls";
        String time = "2020-01-01 10:00:00";

        demo.publishBlog(id, title, content, author, time);

        //更新一篇博客
        String updatedTitle = "更新后的" + title;
        String updatedContent = "更新后的" + content;

        demo.updateBlog(id, updatedTitle, updatedContent);

        //搜索你的博客，看到你的预览内容
        String previewContent = demo.previewBlog(id);
        System.out.println("看到博客的预览内容：" + previewContent);

        //点击进去查看你的博客的详细内容，并且进行点赞
        List<String> blog = demo.getBlog(id);
        System.out.println("查看博客的详细内容：" + blog);
        demo.likeBlog(id);

        //你自己去查看自己的博客，看看浏览次数和点赞次数
        blog = demo.getBlog(id);
        System.out.println("查看博客的详细内容：" + blog);
    }
}
