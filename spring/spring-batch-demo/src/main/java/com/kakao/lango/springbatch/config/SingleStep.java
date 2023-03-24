package com.kakao.lango.springbatch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SingleStep {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Bean
    public Step Step() {
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step LastStep() {
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Last Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Job Job() {
        Job exampleJob = jobBuilderFactory.get("Job1")
                .start(Step())
                .build();
        return exampleJob;
    }
    @Bean
    public Job MultiStepJob() {
        Job exampleJob = jobBuilderFactory.get("Job1")
                .start(Step())
                .next(LastStep())
                .build();
        return exampleJob;
    }


}
