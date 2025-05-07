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
  implements Mapper<LongWritable, Text, Text, IntWritable> {

  private static final IntWritable one = new IntWritable(1);
  private IntWritable count = new IntWritable();
  private Text token = new Text();
  private Text docUrl = new Text();

  public void map(
    LongWritable key,
    Text value,
    OutputCollector<Text, IntWritable> output,
    Reporter reporter
  ) throws IOException {
    String line = value.toString();
    String[] str = line.split("\\s+");// split line with spaces
    
    if(str[0].equals("URL")){
      docUrl.set(str[1]); // set url as current doc-url
    }
    else if(str[0].equals("TOKEN")){ // We have considered the meaning of the word in question as a token
      token.set(str[1]);
      output.collect(new Text(token+"#"+docUrl), one); // (token#doc-url,1)
    }
    
    
  }
}
