package com.cn.springBatch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * Created by Jason on 2019/2/27.
 * 数据处理
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item);

        if("汉族".equals(item.getNation())){
            item.setNation("01");
        }else{
            item.setNation("02");
        }
        return item;
    }
}
