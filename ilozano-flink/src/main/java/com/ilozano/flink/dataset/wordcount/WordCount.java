package com.ilozano.flink.dataset.wordcount;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;

public class WordCount {

    public static void main(String[] args) throws Exception {
        //set up the execution environment
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
         // For a remote environment
        //RemoteEnvironment remoteEnvironment = RemoteEnvironment.createRemoteEnvironment(String host, int port, String... jarFiles)

        //make parameters available in the web interface. Parameters will be available in each node
        ParameterTool params = ParameterTool.fromArgs(args);
        env.getConfig().setGlobalJobParameters(params);

        //read the text file
        DataSet<String> text = env.readTextFile(params.get("input"));

        //filter all the names starting with N
        DataSet<String> filtered = text.filter(new FilterFunction<String>() {
            public boolean filter(String value) {
                return value.startsWith("N");
            }
        }); //filtered = dataset of [Noman Nipun Noman ...]

        //split the lines in pairs (2-tuples --> max is 25) containing: {word,integer)
        //Tuple is a kind of data type. Have a fix length and cotains a set of fields
        // tokenized = [(Noman,1) (Nipun,1) (Noman,1)]
        DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());

        //group by the touple field "0" and sum up tuple field "1"
        DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(new int[]{0}).sum(1);

        //emit result
        if (params.has("output")) {
            counts.writeAsCsv(params.get("output"), "\n", " ");
            //execut program
            env.execute("WordCount Example");
        }
    }

    //MapFunction will take single input on DataSet and return a single element
    public static final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {
        public Tuple2<String, Integer> map(String value) {
            return new Tuple2(value, Integer.valueOf(1));
        }
    }
}
