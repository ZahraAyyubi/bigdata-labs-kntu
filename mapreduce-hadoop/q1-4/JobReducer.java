import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class JobReducer
  extends MapReduceBase
  implements Reducer<Text, IntWritable, Text, IntWritable> {
  int max = 0;
  private Text keyMax = new Text();
  public void reduce(
      Text key,
      Iterator<IntWritable> values,
      OutputCollector<Text, IntWritable> output,
      Reporter reporter
    ) throws IOException {
      int sum = 0;
      while (values.hasNext()) {
        sum += values.next().get(); // sum all totalPrice that come from different mappers for a specific (type,size) pair
      }
      if(sum> max){
        max = sum;
        keyMax = key;
      }
    }

    // Cleanup() is called after last reduce invocation in the same reduce task.
  protected void cleanup(
      OutputCollector<Text, IntWritable> output) throws IOException,
                InterruptedException {
                   output.collect(keyMax, new IntWritable(max));
        }
}
