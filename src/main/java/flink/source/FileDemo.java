package flink.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FileDemo {
    public static void main(String[] args) throws Exception {
        // 构建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 构建源并且打印
        FileSource<String> source = FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("test-file.txt")).build();
        env.fromSource(source, WatermarkStrategy.noWatermarks(), "test-file").print();

        //执行任务
        env.execute();
    }
}
