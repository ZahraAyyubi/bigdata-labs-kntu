import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.IntWritable.Comparator;
import org.apache.hadoop.io.WritableComparator; 
import org.apache.hadoop.io.WritableComparable; 
public class MyComparator extends WritableComparator {
    protected MyComparator() {
        super( IntWritable.class, true);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        IntWritable key1 = (IntWritable) w1;
        IntWritable key2 = (IntWritable) w2;          
        return -1 * key1.compareTo(key2); // sort desc
    }
}