import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class SecondReduce
  extends MapReduceBase
  implements Reducer<IntWritable, Text,IntWritable, Text> {
  int limit=10;
  int counter=1;
  public void reduce(
    IntWritable key,
    Iterator<Text> values,
    OutputCollector< IntWritable, Text> output,
    Reporter reporter
  ) throws IOException {
    if(counter<=limit){ // just emit 10 frequent tokens
      while (values.hasNext()){
          output.collect(key,values.next()); // emit tokens that have the same count (count,token)
      }
      counter++;
    }
  }
  
}
