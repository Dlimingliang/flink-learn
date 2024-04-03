package flink.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataGenDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataGeneratorSource source = new DataGeneratorSource(
                (GeneratorFunction<Long, String>) value -> "Number" + value,
                Long.MAX_VALUE,
                RateLimiterStrategy.perSecond(1),
                Types.STRING);
        env.fromSource(source, WatermarkStrategy.noWatermarks(), "datagen-source").print();

        env.execute("datagen-demo");
    }
}
