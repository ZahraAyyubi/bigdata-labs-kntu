import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class FirstReduce
  extends MapReduceBase
  implements Reducer<Text, Text, Text, Text> {
  int docCounts = 0;
  
  public void reduce(
    Text key,
    Iterator<Text> values,
    OutputCollector<Text, Text> output,
    Reporter reporter
  ) throws IOException {
    
    

    while (values.hasNext()) {
      docCounts++;
      output.collect(key, values.next()); // (URL, doc-url) => list docs urls in output
    }
    output.collect(new Text("DocsCount: "), new Text(docCounts+"")); // write doc counts  
  }
}
