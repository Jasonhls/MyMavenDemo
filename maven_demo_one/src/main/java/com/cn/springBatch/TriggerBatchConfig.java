package com.cn.springBatch;

/**
 * Created by Jason on 2019/2/28.
 */
//@Configuration
//@EnableBatchProcessing
public class TriggerBatchConfig {
    /*@Bean
    @StepScope
    public FlatFileItemReader<ProxyPerson> reader(@Value("#{jobParameters['input.file.anem']}")String pathToFile){
        FlatFileItemReader<ProxyPerson> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLineMapper(new DefaultLineMapper<ProxyPerson>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"name","age","nation","address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ProxyPerson>(){{
                setTargetType(ProxyPerson.classAndInterface);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ProxyPerson,ProxyPerson> processor(){
        CsvItemProcessor processor = new CsvItemProcessor();
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    @Bean
    public ItemWriter<ProxyPerson> writer(DataSource dataSource){
        JdbcBatchItemWriter<ProxyPerson> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into person " + "(name,age,nation,address)" + "values(name,:age,:nation,:address)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource,PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1){
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .listener(csvJobListener())
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,ItemReader<ProxyPerson> reader,ItemWriter<ProxyPerson> writer,
                      ItemProcessor<ProxyPerson,ProxyPerson> processor){
        return stepBuilderFactory.get("step1")
                .<ProxyPerson,ProxyPerson>chunk(65000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public CsvJobListener csvJobListener(){
        return new CsvJobListener();
    }

    @Bean
    public Validator<ProxyPerson> csvBeanValidator(){
        return new CsvBeanValidator<ProxyPerson>();
    }*/
}
