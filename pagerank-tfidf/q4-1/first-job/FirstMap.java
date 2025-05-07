import java.io.IOException;
import java.util.StringTokenizer;
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

  private static final IntWritable one = new IntWritable(1);

  private Text token = new Text();

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<Text, IntWritable> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    StringTokenizer tokenizer = new StringTokenizer(line);
    while (tokenizer.hasMoreTokens()) {
      if(tokenizer.nextToken().equals("TOKEN")){ // We have considered the meaning of the word in question as a token
        token.set(tokenizer.nextToken());
        output.collect(token, one); // (token,1)
      }
    }
  }
}
