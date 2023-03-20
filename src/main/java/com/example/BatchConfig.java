package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private static final Logger logger = LogManager.getLogger(BatchConfig.class);

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final LowerToUpperTasklet tasklet1;
    private final InsertTableTasklet tasklet2;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            LowerToUpperTasklet tasklet1, InsertTableTasklet tasklet2) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.tasklet1 = tasklet1;
        this.tasklet2 = tasklet2;
    }

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("sampleJob") // Job名を指定
                .flow(lowerToUpperStep())
                .next(insertTableStep())
                .end()
                .build();
    }

    @Bean
    public Step lowerToUpperStep() {
        logger.info("call lowerToUpperStep()");
        return stepBuilderFactory.get("lowerToUpperStep") // Step名を指定
                .tasklet(tasklet1)
                .build();
    }

    @Bean
    public Step insertTableStep() {
        logger.info("call insertTableStep()");
        return stepBuilderFactory.get("insertTableStep") // Step名を指定
                .tasklet(tasklet2)
                .build();
    }
}