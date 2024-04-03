package flink.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class CollectionDemo {

    public static void main(String[] args) throws Exception {
        // 构建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 构建源
        //DataStreamSource<Integer> source = env.fromData(1, 22, 333, 4444, 55555);
        //DataStreamSource<Integer> source = env.fromData(new Integer[]{1, 22, 333, 4444, 55555});
        DataStreamSource<Integer> source = env.fromData(Arrays.asList(1, 22, 333, 4444, 55555));
        // 打印结果
        source.print();
        //执行任务
        env.execute("collection-learn");
    }
}
