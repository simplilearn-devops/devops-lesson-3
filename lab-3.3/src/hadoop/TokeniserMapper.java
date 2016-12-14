package hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokeniserMapper extends Mapper<Object, Text, Text, IntWritable> {
	private static final IntWritable ONE = new IntWritable( 1 ) ;

	@Override
	protected void map( Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context )
			throws IOException, InterruptedException {
		StringTokenizer tokeniser = new StringTokenizer( value.toString() ) ;
		while ( tokeniser.hasMoreTokens() ) {
			context.write( new Text( tokeniser.nextToken() ), ONE ) ;
		}
	}

}
