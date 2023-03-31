package com.kakao.lango.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// 배치 작업을 수행하도록 해주는 어노테이션
@EnableBatchProcessing
@RequiredArgsConstructor
@SpringBootApplication
public class SpringBatchDemoApplication {
    // Job과 Step을 생성할 수 있는 팩토리 클래스를 작성하기
    // spring boot 3.0.x 버전에서는 JobBuilderFactory과 StepBuilderFactory이 deprecated되었다.

//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step step() {
//        return this.stepBuilderFactory.get("step-1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        // 작업 내용 작성
//                        System.out.println("Hello Batch!");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    // step을 가지고 job 수행하기
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("job-1").start(step()).build();
//    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchDemoApplication.class, args);
    }

}
