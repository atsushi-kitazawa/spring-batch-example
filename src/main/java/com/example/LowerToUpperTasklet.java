package com.example;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class LowerToUpperTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        System.out.println("LowerToUpper");
        return RepeatStatus.FINISHED;
    }
}
