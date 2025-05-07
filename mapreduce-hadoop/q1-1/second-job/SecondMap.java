import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SecondMap
  extends MapReduceBase
  implements Mapper<LongWritable, Text, IntWritable, Text> {

  private IntWritable count = new IntWritable();
  private Text pizzaTypeSize = new Text();

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<IntWritable, Text> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    String[] tokens = line.split("\\s+");// split line with spaces
    
    count.set(-1*Integer.parseInt(tokens[1]));
    System.out.println("count: " + count);
    pizzaTypeSize.set(tokens[0]);
    output.collect(count, pizzaTypeSize);
    
  }
}
