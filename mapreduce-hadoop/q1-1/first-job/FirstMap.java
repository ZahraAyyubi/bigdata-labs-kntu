import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class FirstMap
  extends MapReduceBase
  implements Mapper<LongWritable, Text, Text, IntWritable> {

  private Text pizzaTypeSize = new Text();
  private IntWritable quantity = new IntWritable(1);

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<Text, IntWritable> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    String[] elementColumns = line.split(",");
    pizzaTypeSize.set(elementColumns[1] + elementColumns[2]);
    quantity.set(Integer.parseInt(elementColumns[4]));
    output.collect(pizzaTypeSize, quantity);//key: [type,size]    value: [quantity]
    
  }
}
