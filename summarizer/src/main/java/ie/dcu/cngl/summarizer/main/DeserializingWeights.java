package ie.dcu.cngl.summarizer.main;

import ie.dcu.cngl.summarizer.Aggregator;
import ie.dcu.cngl.summarizer.Summarizer;
import ie.dcu.cngl.summarizer.Weighter;
import ie.dcu.cngl.tokenizer.Structurer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class DeserializingWeights {
	public static void main(String[] args) throws IOException {
		ArrayList<Double[]> weights = new ArrayList<Double[]>();
		try {
			FileInputStream fileIn = new FileInputStream(new File("test"));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			weights = (ArrayList<Double[]>) in.readObject();
			in.close();
			fileIn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String text = FileUtils.readFileToString(new File("C:\\Users\\Shane\\Desktop\\long.txt"), "UTF-8");
		Structurer structurer = new Structurer();
		Weighter weighter = new Weighter();
		Aggregator aggregator = new Aggregator();		
		Summarizer summarizer = new Summarizer(structurer, weighter, aggregator);
		summarizer.setNumSentences(3);
		summarizer.setWeights(weights);
		summarizer.setTitle("Cancer treatment");
		String summary = summarizer.summarize(text);
		System.out.println(summary);
	}
}
