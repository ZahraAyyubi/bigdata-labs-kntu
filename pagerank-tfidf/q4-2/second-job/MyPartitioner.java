import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;


import org.apache.hadoop.util.*;

public  class MyPartitioner implements Partitioner< Text, IntWritable >{

   @Override
   public int getPartition(Text key, IntWritable value, int numPartitions){
      String[] str = key.toString().split("#");
      return Math.abs((str[0].toString().hashCode()) % numPartitions); // key:token#docu-url => partitining sensitive to token 
   }

   @Override
   public void configure(JobConf arg0) {

   }
}