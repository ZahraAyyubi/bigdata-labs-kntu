import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class SecondReduce
  extends MapReduceBase
  implements Reducer<Text, IntWritable,Text, FloatWritable> {
  int N = 1000255; // calculated in last job
  FloatWritable idf = new FloatWritable();
  public void reduce(
    Text key,
    Iterator<IntWritable> values,
    OutputCollector< Text, FloatWritable> output,
    Reporter reporter
  ) throws IOException {
    int counter = 0;
    System.out.println("key: "+key);
      while (values.hasNext()){
          counter++;
          System.out.println("value: " +values.next());
      }
      idf.set((float)Math.log(N/counter));

      output.collect(new Text(key.toString().split("#")[0]),idf); //  (token,idf)
  }
  
}
