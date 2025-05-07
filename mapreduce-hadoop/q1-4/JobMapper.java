import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class JobMapper
  extends MapReduceBase
  implements Mapper<LongWritable, Text, Text, IntWritable> {

  private Text month = new Text();
  private IntWritable orderCounts= new IntWritable(1);

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<Text, IntWritable> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    String[] elementColumns = line.split(",");
    month.set(elementColumns[5].split("T")[0].split("-")[2]); // split month from date
    orderCounts.set(Integer.parseInt(elementColumns[4]));//  quantity => orderCounts
    output.collect(month, orderCounts);//key: [month]    value: [orderCounts]
  }
}
