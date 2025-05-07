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
  private Text token = new Text();

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<IntWritable, Text> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    String[] tokens = line.split("\\s+");// split line with spaces
    
    count.set(Integer.parseInt(tokens[1]));
    
    token.set(tokens[0]);
    output.collect(count, token);
    
  }
}
