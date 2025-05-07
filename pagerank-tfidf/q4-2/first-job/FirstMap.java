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
  implements Mapper<LongWritable, Text, Text, Text> {

  private Text url = new Text();
  private Text URL = new Text("URL");

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<Text, Text> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    StringTokenizer tokenizer = new StringTokenizer(line);
    while (tokenizer.hasMoreTokens()) {
      if(tokenizer.nextToken().equals("URL")){ // each doc have a unique url
        url.set(tokenizer.nextToken());
        output.collect(URL, url); // ('URL',doc-url)
      }
    }
  }
}
