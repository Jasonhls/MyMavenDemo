package com.cn.threadAndLock.distributedLock.redisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jason on 2019/3/16.
 */
@Service
public class DatabaseService {

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    MiaoshaMapper miaoshaMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean buy(String goodsCode,String userId){
        //商品数量 减一
        String sql = "update tb_miaosha set goods_nums = goods_nums - 1 where goods_code = '"+goodsCode+"' and goods_nums > 0";
//        int count = jdbcTemplate.update(sql);
        int count = miaoshaMapper.update(goodsCode);
        if(count != 1){
            //秒杀失败，没有扣减成功
            return false;
        }
        return true;
    }

    /**
     * 获取库存数
     * @param goodsCode
     * @return
     */
    public String getCount(String goodsCode){
        //商量数量 减一
        String sql = "select goods_nums from tb_miaosha where goods_code = '" + goodsCode + "'";
//        String count = jdbcTemplate.queryForObject(sql,String.classAndInterface);
        String count = miaoshaMapper.getGoodsNumByGoodsCode(goodsCode) + "";
        return count;
    }

}
